package com.lotte.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lotte.domain.PriorInfo;
import com.lotte.service.PriorService;

@Controller
public class PriorExcelDownload {
	@Autowired
	PriorService priorService;
	@RequestMapping(value = "/priorExcelDown.do")
	public void excelDown(HttpServletResponse response, String type,  String detail,
			String str) throws Exception {
		List<PriorInfo> list;
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(type);
		System.out.println(str);
		if (type.equals("mainPrior")) {
			list = priorService.getPriorList();
		}else if(type.equals("mainSearch")) {
			list = priorService.getPriorByList(str);
		}else {
			list = null;
		}
		
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("크롤링 데이터 리스트");
		Row row = null;
		Cell cell = null;
		int rowNo = 0;

		// 테이블 헤더용 스타일

		CellStyle headStyle = wb.createCellStyle();

		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);

		headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		CellStyle bodyStyle = wb.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		bodyStyle.setAlignment(HorizontalAlignment.CENTER);
		bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		// 테이블 헤더 부분

		row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("상품코드");

		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("롯데 URL");
		

		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("신라 URL");
		

		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("현대 URL");
		

		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("등록일");
		
		cell = row.createCell(5);
		cell.setCellValue("주기");
		cell.setCellStyle(headStyle);
		
		
		

		// 데이터 부분 생성
		for (PriorInfo obj : list) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getProductCode());
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getLotteUrl());
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getShillaUrl());
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getHyundaiUrl());
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(fm.format(obj.getRegisterDate()));
			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getPeriod());
			
		}
		// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=priorInfo_list.xls");

		wb.write(response.getOutputStream());
		wb.close();
	}
}
