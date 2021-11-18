package com.lotte.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity 
public class CrawlingData {
	@Id
	@GeneratedValue
	Integer dataNo;
	String productCode;
	String brandName;
	String productName;
	String lottePrice;
	String shillaPrice;
	String hyundaiPrice;
	String lotteDiscount;
	String shillaDiscount;
	String hyundaiDiscount;
	String lotteDisPrice;
	String shillaDisPrice;
	String hyundaiDisPrice;
	Character lotteStock;
	Character shillaStock;
	Character hyundaiStock;
	Timestamp updateDate;
	
	
}
