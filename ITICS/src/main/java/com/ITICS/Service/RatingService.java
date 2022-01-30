package com.ITICS.Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ITICS.Entity.CompanyReports;
import com.ITICS.Entity.Rating;
import com.ITICS.Entity.Sectors;
import com.ITICS.Repository.RatingRepository;
import com.ITICS.XLSX.XLSXDataService;
import com.aspose.cells.Hyperlink;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

@Service
public class RatingService {
	@Autowired
	XLSXDataService xlsxdataService=new XLSXDataService();

	@Autowired
	RatingRepository ratingRepo;
	
	@PersistenceContext
	EntityManager em;
	
	public void InsertXLSXDataIntoRatingTable(XSSFWorkbook myWorkBook) {
		Integer company_id=0;
		
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = xlsxdataService.GetXLSXSheet(myWorkBook, "Score");

		List<Rating> ratingInsertList=new ArrayList<Rating>();
		int col_num_number=-1;
		int col_num_revenue = -1;
		int col_num_name= -1;
	
		if(mySheet!=null) {
		for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
			Row row=mySheet.getRow(row_num);
			if(row!=null) {
				for(int i=0; i < row.getLastCellNum(); i++){
					if(row.getRowNum()==0) {//0,i
						if(row.getCell(i).getStringCellValue().equals("Number")) {
							col_num_number=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Reveue from Theme")) {
							col_num_revenue=i;
						}
						if(row.getCell(i).getStringCellValue().equals("Name")) {
							col_num_name=i;
						}
					}
			}
				if(row.getRowNum()>0) {//0,i
					Integer ratingid=ratingRepo.getRating_IdbyRating_nameFromRating(row.getCell(col_num_name).getStringCellValue());
					if(ratingid==0) {
						Rating rating=new Rating();
						rating.setName(row.getCell(col_num_name).getStringCellValue());
						rating.setCreated_by("Abhishek");
						ratingRepo.save(rating);
					}
			}
		}
	}
		}
	}
	public String[]  exportRatingTemplateData(HttpServletResponse response) {
		String[] headerdata=new String[2];
		headerdata[0]="Name";
		headerdata[1]="Created_By";
		return headerdata;
	}
	public List<Rating> getAllRatingDetails() {
		return ratingRepo.findAll();
	}
	public List<Rating> getAllRatingDetailsByRatingNameSearch(Rating rating) {
		Query q=em.createNativeQuery("select * from Rating where name LIKE "+"'%"+rating.getName()+"%'",Rating.class);
		return (List<Rating>)q.getResultList();
	}
}
