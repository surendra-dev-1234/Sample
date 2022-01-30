package com.ITICS.Repository;

import java.util.List;

import javax.persistence.Tuple;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import com.ITICS.Entity.CompanyIdentifier;
import com.ITICS.Entity.CompanyReports;

@Repository
public interface CompanyIdentifierRepository extends JpaRepository<CompanyIdentifier, Long> {

	@Query(value="select Id from CompanyIdentifier where Company_Id=?1",nativeQuery=true)
	public Integer getIdByCompanyIdFromCompanyIdentifier(Integer company_id); 

}
