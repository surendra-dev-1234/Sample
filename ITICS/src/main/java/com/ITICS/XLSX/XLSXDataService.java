package com.ITICS.XLSX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XLSXDataService {

	public XSSFWorkbook GetXLSXWorkBook(String xlsxFileName) throws IOException {
		File myFile = new File(xlsxFileName); 
		FileInputStream fis = new FileInputStream(myFile); 
		// Finds the workbook instance for XLSX file 
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
		return myWorkBook;
	}

	public XSSFSheet GetXLSXSheet(XSSFWorkbook myWorkBook,String sheetName) {
		XSSFSheet mySheet = myWorkBook.getSheet(sheetName); 
		return mySheet;
	}
}