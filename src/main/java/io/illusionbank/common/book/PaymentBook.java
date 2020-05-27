package io.illusionbank.common.book;

import java.util.Date;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import lombok.Data;

@Record(name = "PaymentBook")
@Data
public class PaymentBook {
	
	@Field(ordinal = 1, length = 8)
	private String transactionSec;
	
	@Field(ordinal = 2,  length = 12)
	private String transactionBank;
	
	@Field(ordinal = 3, length = 10)
	private Double price;
	
	@Field(ordinal = 4, format="ddMMyyyy", length = 8)
	private Date dueDate;
	
	@Field(ordinal = 5, format="ddMMyyyy", length = 8)
	private Date paymentDate;
	
	@Field(ordinal = 6, length = 8)
	private String account;
	
	@Field(ordinal = 7, length = 8)
	private String agency;
	
	@Field(ordinal = 8, length = 11)
	private String client;
	
	@Field(ordinal = 9, length = 15)
	private String ip;
	
	@Field(ordinal = 10, length = 25)
	private String deviceName;
	
	@Field(ordinal = 11, length = 19)
	private String location;
	
}
