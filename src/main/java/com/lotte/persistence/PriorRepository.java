package com.lotte.persistence;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lotte.domain.PriorInfo;
//CRUD및 SQL문을 실행하는 메소드

public interface PriorRepository extends JpaRepository<PriorInfo,String>{
		public Page<PriorInfo> findByProductCodeStartingWith(String productCode,Pageable page);
		@Query(value="select * from prior_info where product_code like concat(?1,'%')",nativeQuery=true)
		public List<PriorInfo> getPriorList(String productCode);
		
		//url 중복 검사
		@Query("select p from PriorInfo p where (p.lotteUrl = ?1 and p.lotteUrl != '') or (p.hyundaiUrl = ?1 and p.hyundaiUrl != '') or (p.shillaUrl = ?1 and p.shillaUrl != '')")
		public List<PriorInfo> urlDuplication(String url);
		
		
		
		
	 
	
	
} 
