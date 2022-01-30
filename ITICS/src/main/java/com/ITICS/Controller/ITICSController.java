package com.ITICS.Controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ITICS.ConfigurationProperties.FileStorageProperties;
import com.ITICS.Entity.Classification;
import com.ITICS.Entity.Companies;
import com.ITICS.Entity.CompanyReports;
import com.ITICS.Entity.Identifier;
import com.ITICS.Entity.Rating;
import com.ITICS.Entity.Sectors;
import com.ITICS.Entity.Themes;
import com.ITICS.Payload.Response;
import com.ITICS.Service.ClassificationService;
import com.ITICS.Service.CompaniesService;
import com.ITICS.Service.CompanyIdentifierService;
import com.ITICS.Service.CompanyReportsService;
import com.ITICS.Service.FileStorageService;
import com.ITICS.Service.IdentifierService;
import com.ITICS.Service.MappingService;
import com.ITICS.Service.RatingService;
import com.ITICS.Service.SectorsService;
import com.ITICS.Service.ThemesService;
import com.ITICS.XLSX.XLSXDataService;
import com.ITICS.XLSX.XLSXFileCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.springframework.core.io.Resource;
import com.ITICS.ConfigurationProperties.FileStorageProperties;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
public class ITICSController {


	@Autowired
	ThemesService themesService=new ThemesService();

	@Autowired
	SectorsService sectorService=new SectorsService();

	@Autowired
	MappingService mappingService=new MappingService();

	@Autowired
	CompaniesService companiesService=new CompaniesService();

	@Autowired
	RatingService ratingService=new RatingService();

	@Autowired
	CompanyIdentifierService companyIdentifierService=new CompanyIdentifierService();

	@Autowired
	ClassificationService classService=new ClassificationService();

	@Autowired
	CompanyReportsService comprepoService=new CompanyReportsService();

	@Autowired
	FileStorageService fileStorageService;

	@Autowired
	IdentifierService identifierService=new IdentifierService();

	@Autowired
	XLSXFileCreator xlsxfilecreator=new XLSXFileCreator();

	@Autowired
	XLSXDataService xlsxdataService=new XLSXDataService();

	@Autowired
	FileStorageProperties fileStorageProperties=new FileStorageProperties();

	@GetMapping(value="downloadTemplate")
	public void  downloadTemplate(HttpServletResponse response){
		try {
			response.setContentType("application/octet-stream");

			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=Master Data Upload Template.xlsx";
			response.setHeader(headerKey, headerValue);
			Map<String,String[]> headerdataMap=new LinkedHashMap<String,String[]>();
			try {
				String[] headerdata1= themesService.exportThemesTemplateData(response);
				headerdataMap.put("Themes", headerdata1);

				String[] headerdata2=companiesService.exportCompaniesTemplateData(response);
				headerdataMap.put("Companies", headerdata2);

				String[] headerdata3= sectorService.exportSectorsTemplateData(response);
				headerdataMap.put("Sectors", headerdata3);

				String[] headerdata4=ratingService.exportRatingTemplateData(response);
				headerdataMap.put("Ratings", headerdata4);

				String[] headerdata5=identifierService.exportIdentifierTemplateData(response);
				headerdataMap.put("Identifier", headerdata5);

				String[] headerdata6= mappingService.exportMappingTemplateData(response);
				headerdataMap.put("Mapping", headerdata6);

				try {
					xlsxfilecreator.exportdownloadTemplate(response,headerdataMap);
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@PostMapping(value="uploadMasterData")
	public ResponseEntity<String> UploadMasterData(@RequestParam("masterdatafile_upload") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		Resource resource=fileStorageService.loadFileAsResource(fileName);
		String filestatus="";
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<String> responseEntity=null;
		try {
			if(resource.getFile()!=null){
				filestatus="Successfully Uploaded file";
				XSSFWorkbook workbook=xlsxdataService.GetXLSXWorkBook(resource.getFile().getAbsolutePath());
				companiesService.InsertXLSXDataIntoCustomerTableAfterUpload(workbook);
				identifierService.InsertXLSXDataIntoIdentifierTableAfterUpload(workbook);
				themesService.InsertXLSXDataIntoThemesTableAfterUpload(workbook);
				sectorService.InsertXLSXDataIntoSectorTableAfterUpload(workbook);
				responseEntity=new ResponseEntity<String>(
						filestatus, headers, HttpStatus.OK);
			}
			else {
				filestatus="Failed";
				responseEntity=new ResponseEntity<String>(
						filestatus, headers, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return responseEntity;
	}

	@GetMapping("/downloadMasterData")
	public void downloadMasterData(HttpServletResponse response) {
		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Master Data Download.xlsx";
	}

	@GetMapping("/getAllCompaniesDetails")
	public List<Companies> getAllCompaniesDetails() {
		return companiesService.getAllCompaniesDetails();
	}

	@GetMapping("/getAllIdentifierDetails")
	public List<Identifier> getAllIdentifierDetails() {
		return identifierService.getAllIdentifierDetails();
	}

	@GetMapping("/getAllThemesDetails")
	public List<Themes> getAllThemesDetails() {
		return themesService.getAllThemesDetails();
	}

	@GetMapping("/getAllSectorsDetails")
	public List<Sectors> getAllSectorsDetails() {
		return sectorService.getAllSectorsDetails();
	}

	@GetMapping("/getAllRatingDetails")
	public List<Rating> getAllRatingDetails() {
		return ratingService.getAllRatingDetails();
	}

	@PostMapping("/getAllCompaniesDetailsByCompanyNameSearch")
	public List<Companies> getAllCompaniesDetailsByCompanyNameSearch(@RequestBody Companies companies) {
		return companiesService.getAllCompaniesDetailsByCompanyNameSearch(companies);
	}

	@PostMapping("/getAllThemesDetailsByThemeNameSearch")
	public List<Themes> getAllThemesDetailsByThemeNameSearch(@RequestBody Themes themes) {
		return themesService.getAllThemesDetailsByThemeNameSearch(themes);
	}

	@PostMapping("/getAllSectorsDetailsBySectorNameSearch")
	public List<Sectors> getAllSectorsDetailsBySectorNameSearch(@RequestBody Sectors sectors) {
		return sectorService.getAllSectorsDetailsBySectorNameSearch(sectors);
	}
	
	@PostMapping("/getAllRatingDetailsByRatingNameSearch")
	public List<Rating> getAllRatingDetailsByRatingNameSearch(@RequestBody Rating rating) {
		return ratingService.getAllRatingDetailsByRatingNameSearch(rating);
	}
	
	@PostMapping("/getAllCompaniesDetailsByCompanyNameSearch2")
	public Map<String, Object> getAllCompaniesDetailsByCompanyNameSearch2(@RequestBody Companies companies) {
		return companiesService.getAllCompaniesDetailsByCompanyNameSearch2(companies);
	}

//	@PostMapping("/getAllThemesDetailsByThemeNameSearch2")
//	public List<Themes> getAllThemesDetailsByThemeNameSearch2(@RequestBody Themes themes) {
//		return themesService.getAllThemesDetailsByThemeNameSearch2(themes);
//	}
//
//	@PostMapping("/getAllSectorsDetailsBySectorNameSearch2")
//	public List<Sectors> getAllSectorsDetailsBySectorNameSearch2(@RequestBody Sectors sectors) {
//		return sectorService.getAllSectorsDetailsBySectorNameSearch2(sectors);
//	}
//	
//	@PostMapping("/getAllRatingDetailsByRatingNameSearch2")
//	public List<Rating> getAllRatingDetailsByRatingNameSearch2(@RequestBody Rating rating) {
//		return ratingService.getAllRatingDetailsByRatingNameSearch2(rating);
//	}

	@PostMapping("/saveAnalysisClassificationData")
	public void saveAnalysisClassificationData(@RequestBody Map<String,Object> classificationdataMap){
		JSONObject jsonObj = new JSONObject(classificationdataMap);
		JSONArray jsonArray= (JSONArray) jsonObj.get("Classification");
		List<Classification> classificationList=new ArrayList<Classification>();
		for (int i = 0; i < jsonArray.length(); i++) {
			Classification objPojo=new Gson().fromJson(jsonArray.get(i).toString(), Classification.class);
			classificationList.add(objPojo);
		}
		CompanyReports companyreports=new Gson().fromJson(jsonObj.get("CompanyReports").toString(), CompanyReports.class);
		Long Id=comprepoService.getIdByCompanyIdYearFromCompanyReports(companyreports.getCompany_id(),companyreports.getYear());
		if(Id==null) {
		comprepoService.saveCompanyReportsData(companyreports);
		}
		classService.saveClassificationData(classificationList);
	}
}
