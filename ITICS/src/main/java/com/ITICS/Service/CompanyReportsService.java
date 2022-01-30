package com.ITICS.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ITICS.Entity.CompanyReports;
import com.ITICS.Repository.CompaniesRepository;
import com.ITICS.Repository.CompanyReportsRepository;
import com.ITICS.XLSX.XLSXDataService;
import com.aspose.cells.Hyperlink;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

@Service
public class CompanyReportsService {
	@Autowired
	XLSXDataService xlsxdataService=new XLSXDataService();

	@Autowired
	CompaniesRepository compRepo;
	
	@Autowired
	CompanyReportsRepository comprepRepo;

	public void InsertXLSXDataIntoCompanyReportsTable(XSSFWorkbook myWorkBook,String xlsxfilename) throws Exception {
		Integer company_id=0;
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = xlsxdataService.GetXLSXSheet(myWorkBook, "ITICS - Consumer Services");
		// Instantiating a Workbook object
				Workbook threadedWorkbook = new Workbook(xlsxfilename);
		Worksheet threadedworksheet = threadedWorkbook.getWorksheets().get("ITICS - Consumer Services");

		List<CompanyReports> companyReportsInsertList=new ArrayList<CompanyReports>();
		int col_num_analyst=-1;
		int col_num_companyname = -1;
		int col_num_annualreportfy= -1;
		int col_num_comments=-1;
		int col_num_link=-1;
		int col_num_qualitycheck=-1;
		int col_num_qualitycheckanalyst=-1;
		for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
			Row row=mySheet.getRow(row_num);
			if(row!=null) {
				for(int i=0; i < row.getLastCellNum(); i++){
					if(row.getRowNum()==0) {//0,i
						if(row.getCell(i).getStringCellValue().equals("Analyst")) {
							col_num_analyst=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Company Name")) {
							col_num_companyname=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Latest Financial Report")) {
							col_num_annualreportfy=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Comments")) {
							col_num_comments=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Quality Check Done (Y/N)")) {
							col_num_qualitycheck=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Quality Check Analyst")) {
							col_num_qualitycheckanalyst=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Link")) {
							col_num_link=i;
						}
					}
				}
				if(row.getRowNum()>0) {
					if(col_num_companyname!=-1 && col_num_annualreportfy!=-1 && col_num_comments!=-1 && col_num_link!=1) {
						company_id=compRepo.getCompanyIdByname(row.getCell(col_num_companyname).toString());
						if(company_id>0) {
							Integer id=comprepRepo.getIdByCompanyIdFromCompanyReports(company_id);
							if(id==0) {
							CompanyReports companyreports=new CompanyReports();
							companyreports.setCompany_id(company_id);
							System.out.println(row.getCell(col_num_annualreportfy));
							if(row.getCell(col_num_annualreportfy)!=null) {
								if(row.getCell(col_num_annualreportfy).toString()!="") {
									if(row.getCell(col_num_annualreportfy).getCellType()==Cell.CELL_TYPE_NUMERIC){
									int year=(int)row.getCell(col_num_annualreportfy).getNumericCellValue();
									companyreports.setYear(String.valueOf(year));
									}
									else {
										companyreports.setYear(row.getCell(col_num_annualreportfy).toString());
									}
								}
							}
							for(Object obj:threadedworksheet.getHyperlinks()) {
								Hyperlink hyperlink=(Hyperlink) obj;
								if(hyperlink!=null) {
								if(hyperlink.getArea().StartRow==row_num && hyperlink.getArea().StartColumn==col_num_link) {
									companyreports.setLocation(hyperlink.getAddress());
								}
								}
							}
							if(row.getCell(col_num_link)!=null) {
								if(row.getCell(col_num_link).getHyperlink()!=null) {
									System.out.println(row.getCell(col_num_link).getHyperlink().getAddress());
								}
							}
							if(row.getCell(col_num_comments)!=null)
								companyreports.setComments(row.getCell(col_num_comments).toString());
							if(row.getCell(col_num_analyst)!=null)
								companyreports.setCreated_by(row.getCell(col_num_analyst).toString());
							if(row.getCell(col_num_qualitycheck)!=null)
								companyreports.setQc_status(row.getCell(col_num_qualitycheck).toString());
							if(row.getCell(col_num_qualitycheckanalyst)!=null)
								companyreports.setQc_by(row.getCell(col_num_qualitycheckanalyst).toString());
							comprepRepo.save(companyreports);
							System.out.println(companyreports);
						}
						}
					}
				}
			}
		}
	}
	

	public Long getIdByCompanyIdYearFromCompanyReports(Integer company_id, String year) {
		return comprepRepo.getIdByCompanyIdYearFromCompanyReports(company_id,year);
	}
	public void saveCompanyReportsData(CompanyReports companyreport) {
	comprepRepo.save(companyreport);
	}
}
