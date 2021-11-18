package com.lotte.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lotte.domain.PriorInfo;



public interface PriorService {

	void insertPrior(PriorInfo prior);

	//전체목록 페이지로 가져오기
	Page<PriorInfo> getPriorPage(Pageable page);
	//전체목록 리스트로 가져오기
    List<PriorInfo> getPriorList();

    //특정 엔티티 가져오기
	Page<PriorInfo> getPrior(String str,Pageable page);
	List<PriorInfo> getPriorByList(String str);
	
	//크롤링 데이터 업데이트
	void updatePrior(PriorInfo prior);

	//크롤링 데이터 삭제
	void deletePrior(PriorInfo prior);
	
	//상품코드 중복체크
	String duplicationCheck(String str);
	
	//롯데 url 중복 검사
	public List<PriorInfo> urlDuplication(String url);
	

}