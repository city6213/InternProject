package com.lotte.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lotte.domain.CrawlingData;
import com.lotte.service.CrawlingDataService;

@Controller
public class CrawlingDataController {
	@Autowired
	CrawlingDataService crawlingDataService;

	// 메인페이지 컨트롤러
	@RequestMapping("/index_compareprice")
	public String getCrawlingList(Model model, @PageableDefault(size = 10) Pageable pageable) {
		Page<CrawlingData> crawlingDataPage = crawlingDataService.getLatestData(pageable);
		//Page<CrawlingData> crawlingDataPage = new PageImpl<CrawlingData>(crawlingDataList,pageable,crawlingDataList.size());
		for (CrawlingData cl : crawlingDataPage) {
			System.out.println(cl.toString());
		}
		
		model.addAttribute("crawlingDataList", crawlingDataPage);
		model.addAttribute("total", crawlingDataPage.getTotalElements());
		model.addAttribute("totalPages", crawlingDataPage.getTotalPages());
		model.addAttribute("nowPage", crawlingDataPage.getNumber());
		model.addAttribute("type","main");
		return "index_compareprice";
	}
	
	//할인가 관리대상 컨트롤러
	@RequestMapping("/getComparedPriceList")
	public String getComparedPriceList(Model model, @PageableDefault(size = 10) Pageable pageable) {
		Page<CrawlingData> comparedPricePage= crawlingDataService.getComparedPricePage(pageable);
		for(CrawlingData list : comparedPricePage) {
			System.out.println(list.getLotteDisPrice()+"|"+list.getShillaDisPrice()+"|"+list.getHyundaiDisPrice());
		}
		model.addAttribute("crawlingDataList", comparedPricePage);
		model.addAttribute("total", comparedPricePage.getTotalElements());
		model.addAttribute("totalPages", comparedPricePage.getTotalPages());
		model.addAttribute("nowPage", comparedPricePage.getNumber());
		model.addAttribute("type","compared");
		return "index_compareprice";
	}

	//메인페이지 검색
	@RequestMapping(value="/detailCrawlingData",params="type=main")
	public String detailCrawlingData(Model model, String str,String type, @PageableDefault(size = 10, page = 0) Pageable pageable) {

		System.out.println(str);
		Page<CrawlingData> crawlingDataList = crawlingDataService.getDetailData(pageable, str);
		for (CrawlingData da : crawlingDataList) {

		}
		model.addAttribute("search", "true");
		model.addAttribute("str", str);
		model.addAttribute("crawlingDataList", crawlingDataList);
		model.addAttribute("nowPage", crawlingDataList.getNumber());
		model.addAttribute("totalPages", crawlingDataList.getTotalPages());
		model.addAttribute("total", crawlingDataList.getTotalElements());
		model.addAttribute("type",type);
		model.addAttribute("detail","true");
		
		
		return "index_compareprice";
	}
	//할인가 관리대상 검색
	@RequestMapping(value="/detailCrawlingData",params="type=compared")
	public String detailSaledCrawlingData(Model model, String str,String type, @PageableDefault(size = 10, page = 0) Pageable pageable) {

		System.out.println(str);
		Page<CrawlingData> crawlingDataList = crawlingDataService.getComparedPriceSeachList(str, pageable);
		for (CrawlingData da : crawlingDataList) {

		}
		model.addAttribute("search", "true");
		model.addAttribute("str", str);
		model.addAttribute("crawlingDataList", crawlingDataList);
		model.addAttribute("nowPage", crawlingDataList.getNumber());
		model.addAttribute("totalPages", crawlingDataList.getTotalPages());
		model.addAttribute("total", crawlingDataList.getTotalElements());
		model.addAttribute("type",type);
		model.addAttribute("detail","true");
		
		
		return "index_compareprice";
	}

	// 특정날짜 데이터 불러오기
	@RequestMapping("/getDateCrawlingData")
	public String getDateCrawlingData(Model model, String str, String date1, String date2,
			@PageableDefault(size = 10, page = 0) Pageable pageable) {
		String date1param = date1+" 00:00:00";
		String date2param = date2+" 23:59:59";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateFrom = null;
		Date dateTo = null;
		try {
			dateFrom = dateFormat.parse(date1param);
			dateTo = dateFormat.parse(date2param);
		} catch (Exception e) {
		}
		System.out.println(dateFrom);
		System.out.println(dateTo);
		Page<CrawlingData> crawlingDataList = crawlingDataService.getDateCrawlingDataList(str, dateFrom, dateTo,
				pageable);

		for (CrawlingData p : crawlingDataList) {
			System.out.println(p.toString());
		}
		model.addAttribute("crawlingDataList", crawlingDataList);
		model.addAttribute("nowPage", crawlingDataList.getNumber());
		model.addAttribute("totalPages", crawlingDataList.getTotalPages());
		model.addAttribute("total", crawlingDataList.getTotalElements());
		model.addAttribute("str", str);
		model.addAttribute("date1param", date1param.replaceAll(" 00:00:00", ""));
		model.addAttribute("date2param", date2param.replaceAll(" 23:59:59", ""));
		model.addAttribute("type","date");
		return "crawlingDataDetail";
	}

	// 날짜 검색 페이지로 이동
	@RequestMapping("/goCrawlingDataDetail")
	public String goCrawlingDataDetail(String str, Integer id, Model model,
			@PageableDefault(size = 10, page = 0) Pageable page) {
		System.out.println(id);
		System.out.println(str);
		//Page<CrawlingData> crawlingDataList = crawlingDataService.getOneByPage(id, page);
		//model.addAttribute("crawlingDataList",crawlingDataList);
		//model.addAttribute("nowPage", crawlingDataList.getNumber());
		//model.addAttribute("totalPages", crawlingDataList.getTotalPages());
		// model.addAttribute("total", "");
		model.addAttribute("str", str);

		return "crawlingDataDetail";
	}

}
