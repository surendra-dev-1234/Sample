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
import com.ITICS.Entity.Identifier;
import com.ITICS.Entity.Themes;
import com.ITICS.Repository.SectorsRepository;
import com.ITICS.Repository.ThemesRepository;
import com.ITICS.XLSX.XLSXDataService;
import com.ITICS.XLSX.XLSXFileCreator;

@Service
public class ThemesService{

	@Autowired
	XLSXDataService xlsxDataService=new XLSXDataService();

	@Autowired
	XLSXFileCreator xlsxfilecreator=new XLSXFileCreator();
	

	@Autowired
	SectorsRepository secRepo;

	@Autowired
	ThemesRepository themesRepo;

	@PersistenceContext
	EntityManager em;
	
	public void InsertXLSXDataIntoThemesTable(XSSFWorkbook myWorkBook) {
		Integer sector_id=0;
		Integer theme_id=0;
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = xlsxDataService.GetXLSXSheet(myWorkBook,"Themes" );

		Iterator<Row> rowIterator = mySheet.iterator(); 

		int col_num_theme=-1;
		int col_num_factsetsector = -1;
		int col_num_description= -1;
		int count=1;
		int count_companyreport=0;
		for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
			Row row=mySheet.getRow(row_num);
			Themes themes=new Themes();
			if(row!=null) {
				System.out.println(row.getLastCellNum());
				for(int i=0; i < row.getLastCellNum(); i++)
				{
					if(row.getRowNum()==0) {
						if(row.getCell(i).getStringCellValue().equals("Theme")) {
							col_num_theme=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Factset Sectors")) {
							col_num_factsetsector=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Description")) {
							col_num_description=i;
						}
					}
				}
				if(row.getRowNum()>0) {
					if(col_num_theme!=-1 && col_num_factsetsector!=-1 && col_num_description!=-1 ) {
						if(row.getCell(col_num_theme)!=null && row.getCell(col_num_factsetsector)!=null) {
							theme_id=themesRepo.getThemeIdByThemeName(row.getCell(col_num_theme).toString());
							sector_id=secRepo.getSectorIdBySectorName(row.getCell(col_num_factsetsector).toString());
							if(theme_id==0) {
								if(sector_id>0) {
									//themes.setSector_Id(sector_id);
									themes.setTheme_name(row.getCell(col_num_theme).toString());
									themes.setDescription(row.getCell(col_num_description).toString());
									themes.setCreated_by("Abhishek Gupta");
									themesRepo.save(themes);
								}
							}
						}
					}
				}
			}
		}
	}

	public String[]  exportThemesTemplateData(HttpServletResponse response) {
		String[] headerdata=new String[4];
		headerdata[0]="Theme_Name";
		headerdata[1]="Description";
		headerdata[2]="Key_Words";
		headerdata[3]="Created_By";
		return headerdata;
	}
	
	public void InsertXLSXDataIntoThemesTableAfterUpload(XSSFWorkbook myWorkBook) {
		Integer sector_id=0;
		Integer theme_id=0;
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = xlsxDataService.GetXLSXSheet(myWorkBook,"Themes" );

		Iterator<Row> rowIterator = mySheet.iterator(); 

		int col_num_theme=-1;
		int col_num_factsetsector = -1;
		int col_num_description= -1;
		int count=1;
		int count_companyreport=-1;
		int col_num_keywords=-1;
		int col_num_createdby=-1;
		//	Description	Key_Words	

		for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
			Row row=mySheet.getRow(row_num);
			
			if(row!=null) {
				for(int i=0; i < row.getLastCellNum(); i++)
				{
					if(row.getRowNum()==0) {
						if(row.getCell(i).getStringCellValue().equals("Theme_Name")) {
							col_num_theme=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Description")) {
							col_num_description=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Key_Words")) {
							col_num_keywords=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Created_By")) {
							col_num_createdby=i;
						}
					}
				}
				if(row.getRowNum()>0) {
					if(col_num_theme!=-1 && col_num_keywords!=-1 && col_num_description!=-1 && col_num_createdby!=-1) {
							theme_id=themesRepo.getThemeIdByThemeName(row.getCell(col_num_theme).toString());
							if(theme_id==null) {
									Themes themes=new Themes();
									themes.setTheme_name(row.getCell(col_num_theme).toString());
									themes.setDescription(row.getCell(col_num_description).toString());
									if(row.getCell(col_num_keywords)!=null)
									themes.setKey_words(row.getCell(col_num_keywords).toString());
									themes.setCreated_by(row.getCell(col_num_createdby).toString());
									themesRepo.save(themes);
									System.out.println(themes);
							}
						}
					}
				}
			}
		}

	public List<Themes> getAllThemesDetails() {
		return themesRepo.findAll();
	}

	public List<Themes> getAllThemesDetailsByThemeNameSearch(Themes themes) {
			Query q=em.createNativeQuery("select * from Themes where theme_name LIKE "+"'%"+themes.getTheme_name()+"%'",Themes.class);
			return (List<Themes>)q.getResultList();
	}
	}

