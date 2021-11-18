package com.lotte.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lotte.domain.CrawlingData;
import com.lotte.service.CrawlingDataService;
import com.lotte.service.PriorService;

@Controller
public class ExcelDownload {
	@Autowired
	CrawlingDataService crawlingDataService;
	PriorService priorService;
	@RequestMapping(value = "/excelDown.do")
	public void excelDown(HttpServletResponse response, String type, String productName, String date1, String date2, String detail,
			String str, Pageable page) throws Exception {
		
		System.out.println(type);
		System.out.println(detail);
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<CrawlingData> list;
		if (type.equals("main") && !detail.equals("true")) {
			list = crawlingDataService.getLatestDataList();
		} else if (type.equals("date")) {

			Date dateFrom = null;
			Date dateTo = null;
			try {
				dateFrom = fm.parse(date1);
				dateTo = fm.parse(date2);
				System.out.println(dateFrom);
				System.out.println(dateTo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list = crawlingDataService.dateList(dateFrom, dateTo, productName);
		} else if (type.equals("compared") && !detail.equals("true")) {
			list = crawlingDataService.getComparedPriceList();
		} else if (type.equals("main") && detail.equals("true")) {
			list = crawlingDataService.getDetailDataListByExcel(str);
		} else if(type.equals("compared") && detail.equals("true")) {
			list = crawlingDataService.getComparedPriceSeachListByExcel(str);
		} else {
			list = null;
		}

		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("크롤링 데이터");
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
		cell.setCellValue("상품명");
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("정상가");
		cell = row.createCell(2);
		cell = row.createCell(3);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 3));

		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("할인율");
		cell = row.createCell(5);
		cell = row.createCell(6);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 6));

		cell = row.createCell(7);
		cell.setCellStyle(headStyle);
		cell.setCellValue("할인가");
		cell = row.createCell(8);
		cell = row.createCell(9);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 9));

		cell = row.createCell(10);
		cell.setCellStyle(headStyle);
		cell.setCellValue("품절여부");
		cell = row.createCell(11);
		cell = row.createCell(12);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 12));
		cell = row.createCell(13);
		cell.setCellValue("데이터 수집 시각");
		cell.setCellStyle(headStyle);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 13, 14));
		cell = row.createCell(14);
		cell.setCellStyle(bodyStyle);
		cell.setCellValue("");

		row = sheet.createRow(rowNo++);
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("롯데");
		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("신라");
		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("현대");
		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("롯데");
		cell = row.createCell(5);
		cell.setCellStyle(headStyle);
		cell.setCellValue("신라");
		cell = row.createCell(6);
		cell.setCellStyle(headStyle);
		cell.setCellValue("현대");
		cell = row.createCell(7);
		cell.setCellStyle(headStyle);
		cell.setCellValue("롯데");
		cell = row.createCell(8);
		cell.setCellStyle(headStyle);
		cell.setCellValue("신라");
		cell = row.createCell(9);
		cell.setCellStyle(headStyle);
		cell.setCellValue("현대");
		cell = row.createCell(10);
		cell.setCellStyle(headStyle);
		cell.setCellValue("롯데");
		cell = row.createCell(11);
		cell.setCellStyle(headStyle);
		cell.setCellValue("신라");
		cell = row.createCell(12);
		cell.setCellStyle(headStyle);
		cell.setCellValue("현대");
		cell = row.createCell(14);
		cell.setCellStyle(bodyStyle);
		cell.setCellValue("");

		// 데이터 부분 생성
		for (CrawlingData obj : list) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getProductName());
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getLottePrice());
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getShillaPrice());
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getHyundaiPrice());
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getLotteDiscount());
			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getShillaDiscount());
			cell = row.createCell(6);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getHyundaiDiscount());
			cell = row.createCell(7);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getLotteDisPrice());
			cell = row.createCell(8);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getShillaDisPrice());
			cell = row.createCell(9);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getHyundaiDisPrice());
			cell = row.createCell(10);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getLotteStock().toString());
			cell = row.createCell(11);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getShillaStock().toString());

			cell = row.createCell(12);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(obj.getHyundaiStock().toString());

			cell = row.createCell(13);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(fm.format(obj.getUpdateDate()));
			sheet.addMergedRegion(new CellRangeAddress(rowNo - 1, rowNo - 1, 13, 14));

			cell = row.createCell(14);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue("");
		}
		// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=crawlingData_list.xls");

		wb.write(response.getOutputStream());
		wb.close();
	}
}
