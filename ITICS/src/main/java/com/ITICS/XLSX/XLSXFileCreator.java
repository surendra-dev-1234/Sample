package com.ITICS.XLSX;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class XLSXFileCreator {
	 private XSSFWorkbook workbook;
	    private XSSFSheet sheet;
	    
	    public XSSFWorkbook createworkBook(){
	    	workbook=new XSSFWorkbook();
	    	return workbook;
	    }
	 private void writeHeaderLine(Map<String,String[]>headerdataMap) {
		    workbook=createworkBook();
		    XSSFCellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(11);
	        style.setFont(font);
	        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        
	        
            for(String sheetName:headerdataMap.keySet()) {
            	int col_num=-1;
	        sheet = workbook.createSheet(sheetName);
	        Row row = sheet.createRow(0);
	        for(String headerdata:headerdataMap.get(sheetName)) {
	        	col_num=col_num+1;
	        createCell(row, col_num, headerdata, style);   
	        }
            }
	    }
	     
	    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (value instanceof Integer) {
	            cell.setCellValue((Integer) value);
	        } else if (value instanceof Boolean) {
	            cell.setCellValue((Boolean) value);
	        }else {
	            cell.setCellValue((String) value);
	        }
	        cell.setCellStyle(style);
	    }
	     
	    private void writeDataLines() {
	        int rowCount = 1;
	 
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);
	    }
	     
	    public void exportdownloadTemplate(HttpServletResponse response,Map<String,String[]>headerdataMap){
	        writeHeaderLine(headerdataMap);
	        ServletOutputStream outputStream=null;
			try {
				outputStream = response.getOutputStream();
				workbook.write(outputStream);
				workbook.close();
				outputStream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	         
	    }
}
