package com.ITICS.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ITICS.Entity.Mapping;
import com.ITICS.Repository.MappingRepository;
import com.ITICS.Repository.SectorsRepository;
import com.ITICS.Repository.ThemesRepository;

@Service
public class MappingService {

	@Autowired
	ThemesRepository theRepo;
	
	@Autowired
	SectorsRepository secRepo;
	
	@Autowired
	MappingRepository mappingRepo;
	
	
	public String[]  exportMappingTemplateData(HttpServletResponse response) {
		String[] headerdata=new String[2];
		headerdata[0]="ThemeId";
		headerdata[1]="SectorId";
		return headerdata;
	}
	

}
