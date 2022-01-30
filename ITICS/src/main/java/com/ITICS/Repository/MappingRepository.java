package com.ITICS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ITICS.Entity.Mapping;

public interface MappingRepository extends JpaRepository<Mapping, Long>{
	

}
