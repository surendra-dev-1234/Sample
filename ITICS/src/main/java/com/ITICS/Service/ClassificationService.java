package com.ITICS.Service;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ITICS.Service.ClassificationService;
import com.ITICS.Service.CompaniesService;
import com.ITICS.Service.SectorsService;
import com.ITICS.Service.ThemesService;
import com.ITICS.XLSX.XLSXDataService;
import com.ITICS.Entity.Classification;
import com.ITICS.Repository.ClassificationRepository;
import com.ITICS.Repository.CompaniesRepository;
import com.ITICS.Repository.ThemesRepository;
import com.aspose.cells.Hyperlink;
import com.aspose.cells.HyperlinkCollection;
import com.aspose.cells.ThreadedComment;
import com.aspose.cells.ThreadedCommentCollection;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

@Service
public class ClassificationService {
	@Autowired
	XLSXDataService xlsxdataService=new XLSXDataService();

	@Autowired
	CompaniesRepository compRepo;

	@Autowired
	ThemesRepository themesRepo;

	@Autowired
	ClassificationRepository classRepo;

	public void InsertXLSXDataIntoClassificationTable(XSSFWorkbook myWorkBook,String xlsxfilename) throws Exception {
		// Return first sheet from the XLSX workbook 
		XSSFSheet mySheet = xlsxdataService.GetXLSXSheet(myWorkBook, "ITICS - Consumer Services");

		// Instantiating a Workbook object
		Workbook threadedWorkbook = new Workbook(xlsxfilename);
		//Access first worksheet
		Worksheet threadedworksheet = threadedWorkbook.getWorksheets().get("ITICS - Consumer Services");

		int inital_col=-1;
		int col_num_companyname=-1;
		int col_num_year=-1;
		int col_num_analyst=-1;
		int col_comments=-1;
		int count=0;

		for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
			Row row=mySheet.getRow(row_num);
			if(row!=null) {
				for(int col_num=0;col_num<row.getLastCellNum();col_num++) {
					if(row_num==0) {
						if(row.getCell(col_num).getStringCellValue().equals("Product as a Service")) {
							inital_col=col_num;
						}
						if(row.getCell(col_num).getStringCellValue().equals("Company Name")) {
							col_num_companyname=col_num;
						}
						if(row.getCell(col_num).getStringCellValue().equals("Latest Financial Report")) {
							col_num_year=col_num;
						}
						if(row.getCell(col_num).getStringCellValue().equals("Analyst")) {
							col_num_analyst=col_num;
						}
						if(row.getCell(col_num).getStringCellValue().equals("Comments")) {
							col_comments=col_num;
						}
					}
				}
			}
		}

		if(inital_col!=-1 && col_num_companyname!=-1 && col_num_year!=-1 && col_num_analyst!=-1) {
			for(int row_num=0;row_num<=mySheet.getLastRowNum();row_num++) {
				Row row=mySheet.getRow(row_num);
				if(row!=null) {
					for(int col_num=inital_col;col_num<row.getLastCellNum();col_num++) {
						if(row_num>348) {
							if(inital_col%2==0 && col_num%2==0) {
								XLSXDataForClassification(mySheet,threadedworksheet,row,count,col_num_companyname,col_num,col_num_year,col_num_analyst,col_comments);
							}
							else {
								if(col_num%2!=0) {
									XLSXDataForClassification(mySheet,threadedworksheet,row,count,col_num_companyname,col_num,col_num_year,col_num_analyst,col_comments);
								}
							}
						}
					}
				}
			}
		}
	}

	private void XLSXDataForClassification(XSSFSheet mySheet,Worksheet threadedWorksheet, Row row,int count,int col_num_companyname,int col_num, int col_num_year, int col_num_analyst, int col_comments) throws Exception {
		if(row.getCell(col_num)!=null && row.getCell(col_num+1)!=null) {
			if(row.getCell(col_num).toString()!="" && row.getCell(col_num+1).toString()!="") {
				count=count+1;
				Integer company_id=compRepo.getCompanyIdByname(row.getCell(col_num_companyname).toString());
				if(company_id>0) {
					Integer theme_id=themesRepo.getThemeIdByThemeName(mySheet.getRow(0).getCell(col_num).toString());
					if(theme_id>0) {
						Integer id=classRepo.getIdByCompanyIDAndThemeIDFromClassification(company_id, theme_id);
						if(id==0){
							Classification classification=new Classification();
							classification.setId((long)count);
							classification.setCompany_id(company_id);
							if(row.getCell(col_num_year)!=null) {
								if(row.getCell(col_num_year).toString()!="") {
									if(row.getCell(col_num_year).getCellType()==Cell.CELL_TYPE_NUMERIC){
										int year=(int)row.getCell(col_num_year).getNumericCellValue();
										classification.setYear(String.valueOf(year));
									}
									else {
										classification.setYear(row.getCell(col_num_year).toString());
									}
								}
							}
							classification.setTheme_id(theme_id);
							if(row.getCell(col_num+1).toString().matches("^\\d+\\.\\d+")) {
								classification.setRevenue(Double.valueOf(row.getCell(col_num+1).toString()));
							}
							if((row.getCell(col_num)).getCellType()!=Cell.CELL_TYPE_STRING) {
								if((int)(row.getCell(col_num)).getNumericCellValue()<=2 && (int)(row.getCell(col_num)).getNumericCellValue()>=0) {
									if((int)(row.getCell(col_num)).getNumericCellValue()==0) {
									classification.setRating_id((int)(row.getCell(col_num)).getNumericCellValue());
									}
									if((int)(row.getCell(col_num)).getNumericCellValue()==1) {
										classification.setRating_id((int)(row.getCell(col_num)).getNumericCellValue());
										}
									if((int)(row.getCell(col_num)).getNumericCellValue()==2) {
										classification.setRating_id((int)(row.getCell(col_num)).getNumericCellValue());
										}
								}
							}
							else {
								row.getCell(col_num).toString().replaceAll("\\s", "");
								if(row.getCell(col_num).toString().matches( "^[0-9]")){
									if(Integer.parseInt(row.getCell(col_num).toString())<=2 && Integer.parseInt(row.getCell(col_num).toString())>=0) {
										if((int)(row.getCell(col_num)).getNumericCellValue()==0) {
											classification.setRating_id((int)(row.getCell(col_num)).getNumericCellValue());
											}
											if((int)(row.getCell(col_num)).getNumericCellValue()==1) {
												classification.setRating_id((int)(row.getCell(col_num)).getNumericCellValue());
												}
											if((int)(row.getCell(col_num)).getNumericCellValue()==2) {
												classification.setRating_id((int)(row.getCell(col_num)).getNumericCellValue());
												}
									}
								}
							}

							//if(row.getCell(col_num+1)!=null) {
								//if(row.getCell(col_num+1).getHyperlink()!=null) {
								//if(row.getCell(col_num+1).getCellComment()!=null) {
								//	System.out.println("url: "+row.getCell(col_num+1).getHyperlink().getAddress());
								//System.out.println("comments: "+row.getCell(col_num+1).getCellComment().getAuthor()+"-->"+row.getCell(col_num+1).getCellComment().getString().toString().replaceAll("\\s+"," ").trim().replace(row.getCell(col_num+1).getCellComment().getString().toString().replaceAll("\\s+"," ").trim().substring(0, row.getCell(col_num+1).getCellComment().getString().toString().replaceAll("\\s+"," ").trim().indexOf("Comment:")+9), "" ))  ;
								// Get Threaded Comments
								HyperlinkCollection hyperlinkCollection=threadedWorksheet.getHyperlinks();
								ThreadedCommentCollection threadedComments = threadedWorksheet.getComments().getThreadedComments(row.getRowNum(), col_num+1);
								String cl_comment="";
								String cl_hyperlink="";
								if(hyperlinkCollection!=null) {
									for(Object obj:hyperlinkCollection) {
										if(obj!=null) {
											Hyperlink hyperlink=(Hyperlink) obj;
											if(hyperlink!=null) {
												if(hyperlink.getArea().StartRow==row.getRowNum() && hyperlink.getArea().StartColumn==col_num+1) {
													if(hyperlink.getAddress()!=null) {
														cl_hyperlink=hyperlink.getAddress();
													}
												}
											}
										}
									}
								}
								if(threadedComments!=null) {
									for (Object obj : threadedComments){
										if(obj!=null) {
											ThreadedComment comment = (ThreadedComment) obj;
											if(comment.getNotes()!=null) {
												cl_comment+="Author: " + comment.getAuthor().getName().toString()+" -> Comment: " + comment.getNotes().trim().trim().replaceAll("\\s+"," ")+";";
											}
										}
									}
								}
								if(!cl_comment.equals("") && !cl_hyperlink.equals("")) {
									classification.setComments(cl_hyperlink+"("+cl_comment+")");
								}
								//}
								if (cl_comment.equals("") && !cl_hyperlink.equals("")){
									classification.setComments(cl_hyperlink);
								}
								if(!cl_comment.equals("") && cl_hyperlink.equals("")) {
									classification.setComments("("+cl_comment+")");
								}
								//}
						//	}
							classification.setCreated_by(row.getCell(col_num_analyst).toString());
//							if(classification.getRating()!=-1 && classification.getRevenue()!=-1.0) {
//								classService.InsertClassificationDetails(classification);
//								System.out.println(classification);
//							}
						}
					}
				}
			}
		}
	}

	private Integer getIdByCompanyIdYearTheme_IdFromCompanyReports(Classification classification) {
		Integer id=classRepo.getIdByCompanyIdYearTheme_IdFromCompanyReports(classification.getCompany_id(),classification.getYear(),classification.getTheme_id());
		return id;
		
	}
	public void saveClassificationData(List<Classification> classificationList) {
	   for(Classification classification:classificationList) {
		   Integer id=getIdByCompanyIdYearTheme_IdFromCompanyReports(classification);
		   if(id==null) {
			   classRepo.save(classification);
		   }
	   }
	}


}
