package com.ITICS.Repository;

import java.util.List;

import javax.persistence.Tuple;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ITICS.Entity.Companies;
import com.ITICS.Entity.CompanyReports;
import com.ITICS.Entity.Rating;

@Repository
public interface CompanyReportsRepository extends JpaRepository<CompanyReports, Long>{
	@Query(value="select Id from CompanyReports where Company_Id=?1",nativeQuery=true)
	public Integer getIdByCompanyIdFromCompanyReports(Integer company_id) ;
	
	@Query(value="select Id from CompanyReports where Company_Id=?1 and year=?2",nativeQuery=true)
	public Long getIdByCompanyIdYearFromCompanyReports(Integer company_id,String year) ;
	
}
