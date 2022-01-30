package com.ITICS.Service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ITICS.Entity.Companies;
import com.ITICS.Entity.CompanyIdentifier;
import com.ITICS.Entity.Identifier;
import com.ITICS.Repository.CompaniesRepository;
import com.ITICS.Repository.CompanyIdentifierRepository;
import com.ITICS.Repository.IdentifierRepository;
import com.ITICS.XLSX.XLSXDataService;

@Service
public class IdentifierService {
	
	@Autowired
	XLSXDataService xlsxdataService=new XLSXDataService();

	@Autowired
	IdentifierRepository idenRepo;
	
	public void InsertXLSXDataIntoIdentifierTableAfterUpload(XSSFWorkbook workbook) {
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = xlsxdataService.GetXLSXSheet(workbook, "Identifier");

		int col_num_companyname=-1;
		int col_num_identifiername=-1;
		int col_num_identifiervalue=-1;
		int col_num_createdby=-1;
		int count=0;
		for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
			Row row=mySheet.getRow(row_num);
			if(row!=null) {
				for(int col_num=0;col_num<row.getLastCellNum();col_num++) {
					if(row_num==0) {
						if(row.getCell(col_num).getStringCellValue().equals("Identifier_Name")) {
							col_num_identifiername=col_num;
						}
						if(row.getCell(col_num).getStringCellValue().equals("Created_By")) {
							col_num_createdby=col_num;
						}
					}
				}
				if(row_num>0) {
					Integer identifierid=idenRepo.getIdentifierIdByIdentifierNameFromIdentifier(row.getCell(col_num_identifiername).toString());
						if(identifierid==null) {
							Identifier identifier=new Identifier();
							identifier.setIdentifier_name(row.getCell(col_num_identifiername).toString());
							if(row.getCell(col_num_createdby)!=null) {
								identifier.setCreated_by(row.getCell(col_num_createdby).toString());
							}
						    idenRepo.save(identifier);
							System.out.println(identifier);
						}
					}
				}
			}
		}
	public String[]  exportIdentifierTemplateData(HttpServletResponse response) {
		String[] headerdata=new String[2];
		headerdata[0]="Identifier_Name";
		headerdata[1]="Created_By";
		return headerdata;
	}
	
	public List<Identifier> getAllIdentifierDetails() {
		return idenRepo.findAll();
	}
	
	}
