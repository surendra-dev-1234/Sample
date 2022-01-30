package com.ITICS.Repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Tuple;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ITICS.Entity.Companies;
import com.ITICS.Entity.Rating;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Long>{
	
	@Query(value="select company_id from Companies where company_name=?1",nativeQuery=true)
	public Integer getCompanyIdByname(String company_name);

//	@Query(value="select * from Companies where company_name LIKE ?1%")
//	public List<Companies> getAllRatingDetailsBySearch(String word); 
}
