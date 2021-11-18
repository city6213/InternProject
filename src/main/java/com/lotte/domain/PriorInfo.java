package com.lotte.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Entity
public class PriorInfo {
	@Id
	@Column(updatable=false,columnDefinition="varchar(45) not null",nullable=false)
	//상품코드
	String productCode;
	@Column(columnDefinition="varchar(200) not null")
	//롯데 URL
	String lotteUrl;
	@Column(columnDefinition="varchar(200) not null")
	//신라 URL
	String shillaUrl;
	@Column(columnDefinition="varchar(200) not null")
	//현대 URL
	String hyundaiUrl;
	@Column(columnDefinition="varchar(45) not null")
	//등록일
	Date registerDate;
	//주기
	Integer period;
	
	
}
