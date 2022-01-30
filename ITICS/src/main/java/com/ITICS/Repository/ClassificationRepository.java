package com.ITICS.Repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Tuple;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ITICS.Entity.Classification;
import com.ITICS.Entity.Companies;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long>{

	@Query(value="select Id from Classification where Company_Id=?1 and Theme_Id=?2",nativeQuery=true)
	public Integer getIdByCompanyIDAndThemeIDFromClassification(Integer companyid, Integer themeid);
	
	@Query(value="select * from Classification where Id=?1",nativeQuery=true)
	public Classification getClassificationDetailsById(Integer id);

	@Query(value="select Id from Classification where Company_Id=?1 and year=?2 and Theme_Id=?3",nativeQuery=true)
	public Integer getIdByCompanyIdYearTheme_IdFromCompanyReports(Integer company_id, String year, Integer theme_id); 
	
}
