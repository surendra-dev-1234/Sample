package com.ITICS.Service;
import com.ITICS.Service.CompaniesService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ITICS.Service.CompaniesService;
import com.ITICS.XLSX.XLSXDataService;
import com.ITICS.Entity.Companies;
import com.ITICS.Entity.Rating;
import com.ITICS.Repository.CompaniesRepository;

@Service
public class CompaniesService {
	@Autowired
	XLSXDataService xlsxdataService=new XLSXDataService();
	
	@Autowired
	CompaniesRepository compRepo;
	
	@PersistenceContext
	EntityManager em;
	
	public void InsertXLSXDataIntoCompaniesTable(XSSFWorkbook myWorkBook) {
		int count=0;
		Integer company_id=0;
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = xlsxdataService.GetXLSXSheet(myWorkBook, "ITICS - Consumer Services");

		int col_num_analyst=-1;
		int col_num_companyname = -1;
		int col_num_companyfy=-1;
		int col_num_companybuss_desc=-1;
		for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
			Row row=mySheet.getRow(row_num);
			if(row!=null) {
			for(int i=0; i < row.getLastCellNum(); i++)
			{
				if(row.getRowNum()==0) {//0,i
					if(row.getCell(i).getStringCellValue().equals("Company Name")) {
						col_num_companyname=i;
					}
					if(row.getCell(i).getStringCellValue().equals("Analyst")) {
						col_num_analyst=i;
					}
					if(row.getCell(i).getStringCellValue().equals("Company Financial Year")) {
						col_num_companyfy=i;
					}
					if(row.getCell(i).getStringCellValue().equals("Business Description")) {
						col_num_companybuss_desc=i;
					}
				}
			}
			if(row.getRowNum()>0) {
				if(col_num_companyname!=-1 && col_num_analyst!=-1) {
					if(row.getCell(col_num_companyname)!=null) {
						company_id=compRepo.getCompanyIdByname(row.getCell(col_num_companyname).toString());
						if(company_id==0) {
							Companies companies =new Companies();
							companies.setCompany_name(row.getCell(col_num_companyname).toString());
							if(row.getCell(col_num_analyst)!=null)
							{
								companies.setCreated_by(row.getCell(col_num_analyst).toString());
							}
							if(row.getCell(col_num_companyfy)!=null)
							{
								companies.setFinancial_year(row.getCell(col_num_companyfy).toString());
							}
							if(row.getCell(col_num_companybuss_desc)!=null)
							{
								companies.setDescription(row.getCell(col_num_companybuss_desc).toString());
							}
							System.out.println(companies);
							compRepo.save(companies);
						}
					}
				}
			}
		}
		}
	}
	public String[]  exportCompaniesTemplateData(HttpServletResponse response) {
		String[] headerdata=new String[4];
		headerdata[0]="Company_Name";
		headerdata[1]="Created_By";
		headerdata[2]="Financial_Year";
		headerdata[3]="Description";
		return headerdata;
	}
	
	public void InsertXLSXDataIntoCustomerTableAfterUpload(XSSFWorkbook workbook) {
		int count=0;
		Integer company_id=0;
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = xlsxdataService.GetXLSXSheet(workbook, "Companies");

		int col_num_analyst=-1;
		int col_num_companyname = -1;
		int col_num_companyfy=-1;
		int col_num_companybuss_desc=-1;
		for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
						
			Row row=mySheet.getRow(row_num);
			if(row!=null) {
			for(int i=0; i < row.getLastCellNum(); i++)
			{
				if(row.getRowNum()==0) {//0,i
					if(row.getCell(i).getStringCellValue().equals("Company_Name")) {
						col_num_companyname=i;
					}
					if(row.getCell(i).getStringCellValue().equals("Created_By")) {
						col_num_analyst=i;
					}
					if(row.getCell(i).getStringCellValue().equals("Financial_Year")) {
						col_num_companyfy=i;
					}
					if(row.getCell(i).getStringCellValue().equals("Description")) {
						col_num_companybuss_desc=i;
					}
				}
			}
			if(row.getRowNum()>0) {
				if(col_num_companyname!=-1 && col_num_analyst!=-1) {
					if(row.getCell(col_num_companyname)!=null) {
						company_id=compRepo.getCompanyIdByname(row.getCell(col_num_companyname).toString());
						if(company_id==null) {
							Companies companies =new Companies();
							companies.setCompany_name(row.getCell(col_num_companyname).toString());
							if(row.getCell(col_num_analyst)!=null)
							{
								companies.setCreated_by(row.getCell(col_num_analyst).toString());
							}
							if(row.getCell(col_num_companyfy)!=null)
							{
								companies.setFinancial_year(row.getCell(col_num_companyfy).toString());
							}
							if(row.getCell(col_num_companybuss_desc)!=null)
							{
								companies.setDescription(row.getCell(col_num_companybuss_desc).toString());
							}
							System.out.println(companies);
							compRepo.save(companies);
						}
					}
				}
			}
		}
		}
	}
	
	public List<Companies> getAllCompaniesDetails() {
	return compRepo.findAll();	
	}
	
	public List<Companies> getAllCompaniesDetailsByCompanyNameSearch(Companies companies) {
		Query q=em.createNativeQuery("select * from Companies where company_name LIKE "+"'%"+companies.getCompany_name()+"%'",Companies.class);
		return (List<Companies>)q.getResultList();
	}
	public Map<String, Object> getAllCompaniesDetailsByCompanyNameSearch2(Companies companies) {
		Query q=em.createNativeQuery("select * from Companies where company_name LIKE "+"'%"+companies.getCompany_name()+"%'",Companies.class);
		Map<String,Object> companiesdataMap=new LinkedHashMap<String,Object>();
		Map<String,String> columnsMap= new LinkedHashMap<String,String>();
		columnsMap.put("Id","company_id");
		columnsMap.put("Name","company_name");
		columnsMap.put("Description","description");
		columnsMap.put("Created_By","created_by");
		columnsMap.put("Created_Date","create_date");
		columnsMap.put("Modified_By","modified_by");
		columnsMap.put("Modified_Date","modified_date");
		companiesdataMap.put("Columns",columnsMap);
		companiesdataMap.put("DataSource", (List<Companies>)q.getResultList());
		return companiesdataMap;
	}
}
