package io.illusionbank.common.book;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import lombok.Data;

@Data
@Record(name = "ChargebackBook")
public class ChargebackBook {
	@Field(ordinal = 1, length = 8)
	private String transactionSec;
	
	@Field(ordinal = 2,  length = 12)
	private String transactionBank;
	
	@Field(ordinal = 3, length = 16)
	private String nsu;
	
	@Field(ordinal = 4, length = 15)
	private String ip;
	
	@Field(ordinal = 5, length = 25)
	private String deviceName;
	
	@Field(ordinal = 6, length = 19)
	private String location;
	
	
}
