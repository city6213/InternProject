package com.lotte.persistence;

import java.util.Date;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lotte.domain.CrawlingData;
import com.lotte.domain.CrawlingDataSummary;

public interface CrawlingDataRepository extends JpaRepository<CrawlingData, Integer> {
	// public Page<CrawlingData> findByProductNameStartingWith(String str,Pageable
	// page);

	// 날짜 검색 데이터 가져오기
	@Query("select b from CrawlingData b where b.productName = ?1 and (b.updateDate between ?2 and ?3) and (b.lottePrice != '-' and b.shillaPrice != '-' and b.hyundaiPrice != '-')")
	public List<CrawlingData> searchDate(String productName, Date date, Date date2);
	//public Page<CrawlingData> findByUpdateDateBetweenAndProductNameIs(Date date1,Date date2,String str,Pageable pageable);
	@Query("select b from CrawlingData b where b.productName = ?1 and (b.updateDate between ?2 and ?3) and (b.lottePrice != '-' and b.shillaPrice != '-' and b.hyundaiPrice != '-')")
	public Page<CrawlingData> searchDatePage(String productName, Date date, Date date2,Pageable page);

	
	// 상품별 가장 최신의 데이터 가져오기(페이지로 가져오기)
	@Query("select p from CrawlingData p where updateDate in (select max(c.updateDate) from CrawlingData c group by c.productName) and (p.lottePrice != '-' and p.shillaPrice !='-' and p.hyundaiPrice != '-')")
	public Page<CrawlingData> getLatestDataPage(Pageable page);
	//액셀용(리스트로 가져오기)
	@Query("select p from CrawlingData p where updateDate in (select max(c.updateDate) from CrawlingData c group by c.productName) and (p.lottePrice != '-' and p.shillaPrice !='-' and p.hyundaiPrice != '-')")
	public List<CrawlingData> getLatestDataList();

	
	
	//할인가 관리 대상
	@Query(value="select * from Crawling_Data  where update_Date in (select max(update_Date) from Crawling_Data  group by product_Name having (cast(replace(lotte_Dis_Price,'$','')as unsigned)>cast(replace(shilla_Dis_Price,'$','') as unsigned) or cast(replace(lotte_Dis_Price,'$','')as unsigned)>cast(replace(hyundai_Dis_Price,'$','') as unsigned)) and (shilla_price != '-' and lotte_price != '-' and hyundai_price != '-'))",nativeQuery=true)
	public Page<CrawlingData> getComparedPricePage(Pageable page);
	//액셀용(리스트로 가져오기)
	@Query(value="select * from Crawling_Data  where update_Date in (select max(update_Date) from Crawling_Data  group by product_Name having (cast(replace(lotte_Dis_Price,'$','')as unsigned)>cast(replace(shilla_Dis_Price,'$','') as unsigned) or cast(replace(lotte_Dis_Price,'$','')as unsigned)>cast(replace(hyundai_Dis_Price,'$','') as unsigned)) and (shilla_price != '-' and lotte_price != '-' and hyundai_price != '-'))",nativeQuery=true)
	public List<CrawlingData> getComparedPriceList();
 
	
	
	//할인가 관리 대상에서 검색
	@Query(value="select * from Crawling_Data  where update_Date in (select max(update_Date) from Crawling_Data  group by product_Name having (cast(replace(lotte_Dis_Price,'$','')as unsigned)>cast(replace(shilla_Dis_Price,'$','') as unsigned) or cast(replace(lotte_Dis_Price,'$','')as unsigned)>cast(replace(hyundai_Dis_Price,'$','') as unsigned))  and product_name like concat(?1,'%') and (shilla_price != '-' and lotte_price != '-' and hyundai_price != '-')) ",nativeQuery=true)
	public Page<CrawlingData> getComparedPriceSeachList(String str,Pageable page);
	//액셀용(리스트로 가져오기)
	@Query(value="select * from Crawling_Data  where update_Date in (select max(update_Date) from Crawling_Data  group by product_Name having (cast(replace(lotte_Dis_Price,'$','')as unsigned)>cast(replace(shilla_Dis_Price,'$','') as unsigned) or cast(replace(lotte_Dis_Price,'$','')as unsigned)>cast(replace(hyundai_Dis_Price,'$','') as unsigned))  and product_name like concat(?1,'%')  and (shilla_price != '-' and lotte_price != '-' and hyundai_price != '-'))",nativeQuery=true)
	public List<CrawlingData> getComparedPriceSeachListByExcel(String str);
	
	
	
	//메인페이지에서 검색
	@Query("select c from CrawlingData c where c.updateDate in (select max(c.updateDate) from CrawlingData c group by c.productName) and c.productName like ?1% and (c.lottePrice != '-' or c.shillaPrice !='-' or c.hyundaiPrice != '-')")
	public Page<CrawlingData> getDetailData(String str, Pageable page);
	@Query("select c from CrawlingData c where c.updateDate in (select max(c.updateDate) from CrawlingData c group by c.productName) and c.productName like ?1% and (c.lottePrice != '-' or c.shillaPrice !='-' or c.hyundaiPrice != '-')")
	public List<CrawlingData> getDetailDataListByExcel(String str);
	
	
	/*
	//데이터 분석을 위한 월평균 할인가 가져오기
	@Query(value="select product_name,avg(lotte_dis_price),avg(shilla_dis_price),avg(hyundai_dis_price) from crawling_data group by product_name where product_name = ?1  and (update_date between ?2 and ?3)",nativeQuery=true)
	public List<CrawlingDataSummary> getAnalyzeData(String productName,Date dateFrom,Date To);
	*/
	
	
	
	
	
	
	
	
	
	//특정 데이터 가져오기
	//@Query("select c from CrawlingData c where dataNo = ?1")
	//public Page<CrawlingData> getOneByPage(Integer id,Pageable page);
	
	
	// 현재가 비교 화면에서 검색된 특정 데이터 가져오기
		/*
		 * @Query("select p from CrawlingData p where updateDate in (select min(c.updateDate) from CrawlingData c group by c.productName) and p.dataNo = ?1"
		 * ) public Page<CrawlingData> getSearchData(Integer dataNum,Pageable page); 
		 */

} 
