package io.illusionbank.common.book;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import lombok.Data;

@Data
@Record(name = "TransferBook")
public class TransferBook {
	
	@Field(ordinal = 1, length = 8)
	private String transactionSec;
	
	@Field(ordinal = 2,  length = 12)
	private String transactionBank;
	
	@Field(ordinal = 3, length = 10)
	private Double value;
	
	@Field(ordinal = 4, length = 8)
	private String account;
	
	@Field(ordinal = 5, length = 8)
	private String agency;

	@Field(ordinal = 6, length = 8)
	private String bankFavored;
	
	@Field(ordinal = 7, length = 8)
	private String agencyFavored;
	
	@Field(ordinal = 8, length = 8)
	private String accountFavored;
	
	@Field(ordinal = 9, length = 11)
	private String client;
	
	@Field(ordinal = 10, length = 15)
	private String ip;
	
	@Field(ordinal = 11, length = 25)
	private String deviceName;
	
	@Field(ordinal = 12, length = 19)
	private String location;
}
