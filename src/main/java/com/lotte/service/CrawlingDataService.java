package com.lotte.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lotte.domain.CrawlingData;
import com.lotte.domain.CrawlingDataSummary;

public interface CrawlingDataService {

	Page<CrawlingData> getCrawlingDataList(Pageable pageable);

	//특정 기간동안의 과거이력 조회
	public Page<CrawlingData> getDateCrawlingDataList(String productName, Date date1, Date date2, Pageable pageable);

	public List<CrawlingData> getCrawlingData();

	public Page<CrawlingData> getLatestData(Pageable page);

	public List<CrawlingData> getLatestDataList();

	public Page<CrawlingData> getDetailData(Pageable page, String str);

	//public Page<CrawlingData> getOneByPage(Integer id, Pageable page);
	
	public List<CrawlingData> dateList(Date date1,Date date2,String str);
	
	//할인가가 다른 회사보다 낮은 데이터 가져오기
	public Page<CrawlingData> getComparedPricePage(Pageable page);
	public List<CrawlingData> getComparedPriceList();
	
	
	//할인가 관리대상에서 검색
	public Page<CrawlingData> getComparedPriceSeachList(String str,Pageable page);
	public List<CrawlingData> getComparedPriceSeachListByExcel(String str);
	
	
	//메인페이지에서 검색
	public List<CrawlingData> getDetailDataListByExcel(String str);
//	public Page<CrawlingData> getDateCrawlingDataList(Date date1, Date date2, String str, Pageable pageable);
	
	/*
	//특정일 평균 데이터 분석
	public List<CrawlingDataSummary> getAnalyzeData(String productName,Date dateFrom,Date dateTo);*/
}