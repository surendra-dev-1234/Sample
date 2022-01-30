package com.ITICS.Repository;

import java.util.List;

import javax.persistence.Tuple;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ITICS.Entity.Sectors;

@Repository
public interface SectorsRepository extends JpaRepository<Sectors, Long>{

	@Query(value="select Sector_Id from Sectors where Sector_Name=?1",nativeQuery=true)
	public Integer getSectorIdBySectorName(String sector_name);
}
