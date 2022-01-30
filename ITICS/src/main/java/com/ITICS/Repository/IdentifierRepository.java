package com.ITICS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ITICS.Entity.Identifier;

@Repository
public interface IdentifierRepository extends JpaRepository<Identifier, Long>{

	@Query(value="select Identifier_Id from Identifier where Identifier_Name=?1",nativeQuery=true)
	public Integer getIdentifierIdByIdentifierNameFromIdentifier(String identifername); 
}
