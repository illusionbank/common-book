package io.illusionbank.common;

import java.io.StringWriter;
import java.io.Writer;
import java.time.Instant;
import java.util.Date;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.StreamBuilder;

import io.illusionbank.common.book.PaymentBook;

public class Main {

	public static void main(String[] args) {
		
		PaymentBook book = new PaymentBook();
		book.setTransactionSec("10005007");
		book.setTransactionBank("100060000320");
		book.setPrice(10.67);
		book.setAccount("48023");
		book.setAgency("1");
		book.setDueDate(Date.from(Instant.now().plusSeconds(60*60*24*30)));
		book.setPaymentDate(Date.from(Instant.now()));
		book.setClient("12091678290");
		book.setIp("192.178.90.12");
		book.setLocation("88199888273");
		book.setDeviceName("Mozila x");
		
		
		StreamFactory factory = StreamFactory.newInstance();
		StreamBuilder PaymentOutboundBook = new StreamBuilder("PaymentOutboundBook")
		        .format("fixedlength")
		        .parser(new FixedLengthParserBuilder())
		        .addRecord(PaymentBook.class);
		
		factory.define(PaymentOutboundBook);
		
		Writer writer = new StringWriter();
		BeanWriter out = factory.createWriter("PaymentOutboundBook", writer);
		
		out.write("PaymentOutboundBook", book);
		
		System.out.println(writer.toString());
		

	}

}
