package com.lotte.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lotte.domain.PriorInfo;
import com.lotte.service.PriorService;

@Controller
public class PriorController {
	@Autowired
	PriorService priorService;

	// 메인페이지 호출
	@RequestMapping("/index_crawling")
	public String getPriorList(Model model, @PageableDefault(size = 10, page = 0) Pageable page) {
		Page<PriorInfo> priorList = priorService.getPriorPage(page);
		model.addAttribute("priorList", priorList);
		model.addAttribute("total", priorList.getTotalElements());
		model.addAttribute("totalPages", priorList.getTotalPages());
		model.addAttribute("nowPage", priorList.getNumber());
		model.addAttribute("type", "mainPrior");
		return "index_crawling";
	}

	// 크롤링 등록 페이지
	@RequestMapping("/registCrawlingData")
	public String registCrawlingData() {
		return "registCrawlingData";
	}

	// 특정상품의 테이블 행을 누르면 상세보기로 이동
	@RequestMapping("/detailprior")
	public String detailPrior(Model model, PriorInfo prior) {
		model.addAttribute("prior", prior);
		model.addAttribute("preLotteUrl",prior.getLotteUrl());
		model.addAttribute("preShillaUrl",prior.getShillaUrl());
		model.addAttribute("preHyundaiUrl",prior.getHyundaiUrl());
		return "detailprior";
	}

	// 크롤링 데이터를 업데이트
	@RequestMapping(value = "/updateOrDeletePrior", params = "value=update")
	public String updatePrior(PriorInfo prior) {
		prior.setRegisterDate(new Date());
		priorService.updatePrior(prior);
		return "forward:index_crawling";

	}

	// 크롤링 데이터를 삭제
	@RequestMapping(value = "/updateOrDeletePrior", params = "value=delete")
	public String deletePrior(PriorInfo prior) {
		priorService.deletePrior(prior);
		return "forward:index_crawling";
	}

	// 크롤링 데이터를 등록
	@RequestMapping("/registPrior")
	public String registPrior(PriorInfo prior) {
		prior.setRegisterDate(new Date());
		priorService.insertPrior(prior);
		return "forward:index_crawling";
	}

	// 크롤링 데이터 검색
	@RequestMapping("/searchPrior")
	public String searchPrior(Model model, String str, @PageableDefault(size = 10, page = 0) Pageable page) {
		System.out.println(str);
		Page<PriorInfo> priorList = priorService.getPrior(str, page);
		model.addAttribute("priorList", priorList);
		model.addAttribute("productCode", str);
		model.addAttribute("total", priorList.getTotalElements());
		model.addAttribute("totalPages", priorList.getTotalPages());
		model.addAttribute("nowPage", priorList.getNumber());
		model.addAttribute("searchKeyWord", str);
		model.addAttribute("type","mainSearch");
		if (priorList.getSize() == 0) {
			model.addAttribute("value", "false");
		} else {
			model.addAttribute("value", "true");
		}

		for (PriorInfo pr : priorList) {
			System.out.println(pr.toString());
		}
		return "index_crawling";
	}

	// 상품코드 중복 체크
	@RequestMapping("duplicationCheck")
	public @ResponseBody String duplicationCheck(String str) {
		System.out.println(str);
		String value = priorService.duplicationCheck(str);
		System.out.println(value);
		return value;
	}

	// url 중복 체크
	@RequestMapping("urlDuplication")
	public @ResponseBody String urlDuplication(String lotteUrl, String shillaUrl, String hyundaiUrl) {
		String value = "";
		System.out.println(lotteUrl + " " + shillaUrl + " " + hyundaiUrl);
		List<PriorInfo> lotte = null;
		List<PriorInfo> shilla = null;
		List<PriorInfo> hyundai = null;
		
		if (lotteUrl != null) {
			lotte = priorService.urlDuplication(lotteUrl);
		}
		if (shillaUrl != null) {
			hyundai = priorService.urlDuplication(hyundaiUrl);
		}

		if (hyundaiUrl != null) {
			shilla = priorService.urlDuplication(shillaUrl);
		}
		
		/*
		for (PriorInfo pr1 : lotte) {
			System.out.println(pr1.toString());

		}
		
		System.out.println("========================");
		for (PriorInfo pr2 : shilla) {
			System.out.println(pr2.toString());

		}
		System.out.println("========================");
		for (PriorInfo pr3 : hyundai) {
			System.out.println(pr3.toString());

		}*/
		
		if (lotte.size() != 0) {
			value += "lotte";
		}

		if (shilla.size() != 0) {
			value += "shilla";
		}

		if (hyundai.size() != 0) {
			value += "hyundai";
		}
		System.out.println(value);
		return value;
	}

}
