package io.illusionbank.common.mapper;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Random;

import org.beanio.BeanReader;
import org.beanio.BeanWriter;
import org.beanio.InvalidRecordException;
import org.beanio.StreamFactory;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.StreamBuilder;

import io.illusionbank.common.book.PaymentBook;
import io.illusionbank.common.book.ResponseBook;
import io.illusionbank.common.book.TransferBook;
import io.illusionbank.common.enumeration.BookStatus;
import io.illusionbank.common.exception.InvalidBookException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookMapper {

	private Random random = new Random();

	private String hash() {
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			builder.append(random.nextInt(9));
		}
		return builder.toString();
	}

	public PaymentBook loadPaymentBookfromBookOrNull(String book) throws InvalidBookException {
		BeanReader beanReader = getReader("PaymentBook", PaymentBook.class, book);
		PaymentBook paymentBook = null;
		try {
			Object object = beanReader.read();
			if (object instanceof PaymentBook) {
				paymentBook = (PaymentBook) object;
			}
		} catch (InvalidRecordException e) {
			log.error("Parser Error, {}", e);
			throw new InvalidBookException();
		}
		return paymentBook;
	}

	public TransferBook loadTransferBookfromBookOrNull(String book) throws InvalidBookException {
		BeanReader beanReader =  getReader("TransferBook", TransferBook.class, book);
		TransferBook transferBook = null;
		try {
			Object object = beanReader.read();
			if (object instanceof TransferBook) {
				transferBook = (TransferBook) object;
			}
		} catch (InvalidRecordException e) {
			log.error("Parser Error, {}", e);
			throw new InvalidBookException();
		}
		return transferBook;
	}
	
	public ResponseBook loadResonseBook(String book) {
		BeanReader beanReader =  getReader("ResponseBook", TransferBook.class, book);
		ResponseBook responseBook = null;
		try {
			Object object = beanReader.read();
			if (object instanceof ResponseBook) {
				responseBook = (ResponseBook) object;
			}
		} catch (InvalidRecordException e) {
			log.error("Parser Error, {}", e);
			throw new InvalidBookException();
		}
		return responseBook;
	}

	public String serializeErrorBook(String transactionOrigin) {
		var errorBook = new ResponseBook(BookStatus.FAILURE, hash(), transactionOrigin);
		return getWriter("ResponseBook", ResponseBook.class, errorBook).toString();
	}

	public String serializeResponseBookRamdom(String transactionOrigin) {
		BookStatus[] status = {BookStatus.REJECTED, BookStatus.REVIEW, BookStatus.SUCCESS}; 
		ResponseBook responseBook = new ResponseBook(status[this.random.nextInt(3)], hash(), transactionOrigin);
		return getWriter("ResponseBook", ResponseBook.class, responseBook).toString();
	}
	
	public String serializeTransferBook(TransferBook transferBookUser) {
		return getWriter("TransferBook", TransferBook.class, transferBookUser).toString();
	}
	
	private Writer getWriter(String name, Class<?> type, Object book) {
		StreamFactory factory = getFactory(name, type);
		
		Writer writer = new StringWriter();
		BeanWriter beanWriter = factory.createWriter(name, writer);
		
		beanWriter.write(book);
	
		return writer;
	}
	
	private BeanReader getReader(String name, Class<?> type, String book) {
		return getFactory(name, type).createReader(name, new StringReader(book));
	}
	
	private StreamFactory getFactory(String name, Class<?> type) {
		StreamFactory factory = StreamFactory.newInstance();
		StreamBuilder builder = new StreamBuilder(name).format("fixedlength")
				.parser(new FixedLengthParserBuilder()).addRecord(type);
		factory.define(builder);
		return factory;
	}

}
