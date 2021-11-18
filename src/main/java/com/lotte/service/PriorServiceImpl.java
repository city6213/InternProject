package com.lotte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lotte.domain.PriorInfo;
import com.lotte.persistence.PriorRepository;

@Service
public class PriorServiceImpl implements PriorService {
	@Autowired
	PriorRepository priorRepo;

	// 데이터 삽입
	public void insertPrior(PriorInfo prior) {
		priorRepo.save(prior);
	}

	// 등록된 데이터 목록 가져오기
	public Page<PriorInfo> getPriorPage(Pageable page) {
		Page<PriorInfo> pageList = priorRepo.findAll(page);
		return pageList;

	}
	public List<PriorInfo> getPriorList() {
		
		return priorRepo.findAll(); //액셀용 리스트

	}


	// 특정 데이터 가져오기
	public Page<PriorInfo> getPrior(String str,Pageable page) {
		try {
			str = str.trim();
			System.out.println(str);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return priorRepo.findByProductCodeStartingWith(str, page);
	}
	
	//특정 데이터 리스트로 가져오기
	public List<PriorInfo> getPriorByList(String str) {
		try {
			str = str.trim();
			System.out.println(str);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return priorRepo.getPriorList(str);
		
	}

	// 데이터 업데이트
	public void updatePrior(PriorInfo prior) {
		try {

			System.out.println(prior.getProductCode());
			PriorInfo pr = priorRepo.findById(prior.getProductCode()).get();
			pr.setLotteUrl(prior.getLotteUrl());
			pr.setShillaUrl(prior.getShillaUrl());
			pr.setHyundaiUrl(prior.getHyundaiUrl());
			pr.setPeriod(prior.getPeriod());
			priorRepo.save(pr);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// 데이터 삭제
	public void deletePrior(PriorInfo prior) {
		System.out.println(prior.getProductCode());
		priorRepo.deleteById(prior.getProductCode());
	}

	
	//상품코드 중복체크
	public String duplicationCheck(String str) {

		String value = "true";
		try {
			PriorInfo prior = priorRepo.findById(str).get();
			if (prior != null) {
				return "false";
			}else {
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	
	
	//url 중복체크
	
	public List<PriorInfo> urlDuplication(String url){
		return priorRepo.urlDuplication(url);
	}
	
	


}
