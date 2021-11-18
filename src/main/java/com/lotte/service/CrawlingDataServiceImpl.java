package com.lotte.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lotte.domain.CrawlingData;
import com.lotte.domain.CrawlingDataSummary;
import com.lotte.persistence.CrawlingDataRepository;

@Service
public class CrawlingDataServiceImpl implements CrawlingDataService {
	@Autowired
	CrawlingDataRepository crawlingDataRepo;

	// 모든 데이터 가져오기
	public Page<CrawlingData> getCrawlingDataList(Pageable pageable) {

		return crawlingDataRepo.findAll(pageable);
	}

	/*
	 * public Page<CrawlingData> getDetailDataList(Integer dataNum,Pageable
	 * pageable) { return crawlingDataRepo.getSearchData(dataNum, pageable);
	 * 
	 * }
	 */

	/*public Page<CrawlingData> getDateCrawlingDataList(String productName, Date date1, Date date2, Pageable pageable) {
		return crawlingDataRepo.searchDate(productName, date1, date2, pageable);
	}*/
	public Page<CrawlingData> getDateCrawlingDataList(String productName,Date date1, Date date2,  Pageable pageable) {
		return crawlingDataRepo.searchDatePage(productName ,date1,date2,pageable);
	}

	
	public List<CrawlingData> getCrawlingData() {
		return crawlingDataRepo.findAll();
	}

	public Page<CrawlingData> getLatestData(Pageable page) {
		return crawlingDataRepo.getLatestDataPage(page);
	}

	public List<CrawlingData> getLatestDataList() {
		return crawlingDataRepo.getLatestDataList();
	};

	public Page<CrawlingData> getDetailData(Pageable page, String str) {
		return crawlingDataRepo.getDetailData(str, page);
	}

	/*public Page<CrawlingData> getOneByPage(Integer id, Pageable page) {
		return crawlingDataRepo.getOneByPage(id, page);
	}*/
	
	//특정기간 데이터 가져오기
	public List<CrawlingData> dateList(Date date1,Date date2,String str){
		return crawlingDataRepo.searchDate(str, date1, date2);
	}
	
	public Page<CrawlingData> getComparedPricePage(Pageable page){
		return crawlingDataRepo.getComparedPricePage(page);
	}
	
	
	public List<CrawlingData> getComparedPriceList(){
		return crawlingDataRepo.getComparedPriceList();
	}
	
	
	
	//현재가 비교
	public Page<CrawlingData> getComparedPriceSeachList(String str,Pageable page){
		return crawlingDataRepo.getComparedPriceSeachList(str, page);
	}
	public List<CrawlingData> getComparedPriceSeachListByExcel(String str){
		return crawlingDataRepo.getComparedPriceSeachListByExcel(str);
	}
	
	
	//메인페이지에서 검색
	public List<CrawlingData> getDetailDataListByExcel(String str){
		return crawlingDataRepo.getDetailDataListByExcel(str);
	}
	
	/*
	//특정일 평균 데이터 분석
		public List<CrawlingDataSummary> getAnalyzeData(String productName,Date dateFrom,Date dateTo){
			return crawlingDataRepo.getAnalyzeData(productName, dateFrom, dateTo);
		}
	*/
	
}
