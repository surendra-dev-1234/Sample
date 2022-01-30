package com.ITICS.Service;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ITICS.Entity.Companies;
import com.ITICS.Entity.CompanyIdentifier;
import com.ITICS.Repository.CompaniesRepository;
import com.ITICS.Repository.CompanyIdentifierRepository;
import com.ITICS.XLSX.XLSXDataService;

@Service
public class CompanyIdentifierService {
	@Autowired
	XLSXDataService xlsxdataService=new XLSXDataService();

	@Autowired
	CompaniesRepository compRepo;

	@Autowired
	CompanyIdentifierRepository compIdenRepo;

	public void InsertXLSXDataIntoCompanyIdentifierTable(XSSFWorkbook myWorkBook) {
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = xlsxdataService.GetXLSXSheet(myWorkBook, "ITICS - Consumer Services");
		int col_num_companyname=-1;
		int col_num_identifier=-1;
		int col_num_analyst=-1;
		int count=0;
		int company_id=-1;
		for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
			Row row=mySheet.getRow(row_num);
			if(row!=null) {
				for(int col_num=0;col_num<row.getLastCellNum();col_num++) {
					if(row_num==0) {
						if(row.getCell(col_num).getStringCellValue().equals("Company Name")) {
							col_num_companyname=col_num;
						}
						if(row.getCell(col_num).getStringCellValue().equals("ISIN")) {
							col_num_identifier=col_num;
						}
						if(row.getCell(col_num).getStringCellValue().equals("Analyst")) {
							col_num_analyst=col_num;
						}
					}
				}
				if(row_num>0) {
					System.out.println(row.getCell(col_num_companyname).toString());
					company_id=compRepo.getCompanyIdByname(row.getCell(col_num_companyname).toString()); 
					//System.out.println(row.getRowNum()+","+col_num_companyname+"-->"+company_id+" "+row.getCell(col_num_companyname).toString());
					if(company_id>0) {
						Integer id=compIdenRepo.getIdByCompanyIdFromCompanyIdentifier(company_id);
						System.out.println(id);
						if(id==0) {
							CompanyIdentifier companyidentifier=new CompanyIdentifier();
							count=count+1;
							companyidentifier.setId((long)count);
							companyidentifier.setCompany_id(company_id);
							companyidentifier.setIdentifier_name(mySheet.getRow(0).getCell(col_num_identifier).getStringCellValue());
							if(row.getCell(col_num_identifier)!=null){
								companyidentifier.setIdentifier_value(row.getCell(col_num_identifier).toString());
							}
							if(row.getCell(col_num_analyst)!=null) {
								companyidentifier.setCreated_by(row.getCell(col_num_analyst).toString());
							}
							compIdenRepo.save(companyidentifier);
							System.out.println(companyidentifier);
						}
					}
				}
			}
		}
	}
	
}
