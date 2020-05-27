package io.illusionbank.common.book;

import java.util.Date;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import io.illusionbank.common.enumeration.BookStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Record(name = "ResponseBook")
public class ResponseBook {
	
	@Field(ordinal = 1, length = 8)
	private String transactionSec;
	
	@Field(ordinal = 2, length = 12)
	private String transactionOrigin;
	
	@Field(ordinal = 3, format="ddMMyyyy", length = 8)
	private Date date;
	
	@Field(ordinal = 4, length = 6)
	private Double score;
	
	@Field(ordinal = 5, length = 16)
	private BookStatus status;
	
	@Field(ordinal = 6, length = 8)
	private String hash;
	
	public ResponseBook(BookStatus status, String hash, String transactionOrigin) {
		this.status = status;
		this.hash = hash;
		this.transactionSec = "IBRESERR";
		this.transactionOrigin = transactionOrigin;
		this.date = new Date();
		defineScoreByStatus();
	}
	
	private void defineScoreByStatus() {
		if(status == BookStatus.REJECTED) {
			score = 80.689;
			
		} else if(status == BookStatus.REVIEW) {
			score = 40.679;
			
		} else if(status == BookStatus.SUCCESS) {
			score = 10.878;
		}
	}
}
