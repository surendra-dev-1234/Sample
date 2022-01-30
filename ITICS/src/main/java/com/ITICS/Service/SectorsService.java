package com.ITICS.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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

import com.ITICS.Entity.Companies;
import com.ITICS.Entity.Sectors;
import com.ITICS.Entity.Themes;
import com.ITICS.Repository.SectorsRepository;
import com.ITICS.XLSX.XLSXDataService;


@Service
public class SectorsService {
	@Autowired
	XLSXDataService xlsxdataService=new XLSXDataService();
	
	@Autowired
	SectorsRepository secRepo;

	@PersistenceContext
	EntityManager em;
	
	public void InsertXLSXDataIntoSectorsTable(XSSFWorkbook myWorkBook) {
		XSSFSheet mySheet = xlsxdataService.GetXLSXSheet(myWorkBook, "Sectors");
		int inital_col=-1;
		int col_num_sectorname=-1;
		int col_num_description=-1;
		int col_num_createdby=-1;
		int count=0;
		for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
			Row row=mySheet.getRow(row_num);
			if(row!=null) {
				System.out.println(row.getLastCellNum());
				Sectors sectors=new Sectors();
				for(int col_num=0;col_num<row.getLastCellNum();col_num++) {
					if(row_num==0) {
						if(row.getCell(col_num).getStringCellValue().equals("Sector_Name")) {
							col_num_sectorname=col_num;
						}
						if(row.getCell(col_num).getStringCellValue().equals("Description")) {
							col_num_description=col_num;
						}
					}
				}
				if(row_num>0) {
					Integer sec_id=secRepo.getSectorIdBySectorName(row.getCell(col_num_sectorname).toString());
					if(sec_id==0) {
						count=count+1;
						sectors.setSector_name(row.getCell(col_num_sectorname).toString());
						sectors.setDescription(row.getCell(col_num_description).toString());
						if(count<(row_num)/2) {
							sectors.setCreated_by("Krishan Kumar H");
						}
						else {
							sectors.setCreated_by("Abhishek Gupta");
						}
						System.out.println(sectors);
						secRepo.save(sectors);
					}
				}
			}
		}
	}

	
	public String[]  exportSectorsTemplateData(HttpServletResponse response) {
		String[] headerdata=new String[3];
		headerdata[0]="Sector_Name";
		headerdata[1]="Description";
		headerdata[2]="Created_By";
		return headerdata;
	}
	public void InsertXLSXDataIntoSectorTableAfterUpload(XSSFWorkbook myWorkBook) {
	//	Integer sector_id=0;
		Integer theme_id=0;
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = xlsxdataService.GetXLSXSheet(myWorkBook,"Sectors" );

		Iterator<Row> rowIterator = mySheet.iterator(); 

		int col_num_theme=-1;
		int col_num_factsetsector = -1;
		int col_num_description= -1;
		int count=1;
		int count_companyreport=0;
		int col_num_keywords=0;
		int col_num_createdby=0;
		//	Description	Key_Words	

		for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
			Row row=mySheet.getRow(row_num);
			
			if(row!=null) {
				for(int i=0; i < row.getLastCellNum(); i++)
				{
					if(row.getRowNum()==0) {
						if(row.getCell(i).getStringCellValue().equals("Sector_Name")) {
							col_num_theme=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Description")) {
							col_num_description=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Created_By")) {
							col_num_createdby=i;
						}
					}
				}
				if(row.getRowNum()>0) {
					if(col_num_theme!=-1 && col_num_description!=-1 && col_num_createdby!=-1) {
							Integer sec_id=secRepo.getSectorIdBySectorName(row.getCell(col_num_theme).toString());
							if(sec_id==null) {
									Sectors sectors=new Sectors();
									sectors.setSector_name(row.getCell(col_num_theme).toString());
									sectors.setDescription(row.getCell(col_num_description).toString());
									sectors.setCreated_by(row.getCell(col_num_createdby).toString());
									secRepo.save(sectors);
							}
						}
					}
				}
			}
		}


	public List<Sectors> getAllSectorsDetails() {
		return secRepo.findAll();
	}

	public List<Sectors> getAllSectorsDetailsBySectorNameSearch(Sectors sectors) {
		Query q=em.createNativeQuery("select * from Sectors where sector_name LIKE "+"'%"+sectors.getSector_name()+"%'",Sectors.class);
		return (List<Sectors>)q.getResultList();
}
}
