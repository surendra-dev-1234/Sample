package com.ITICS.Repository;

import java.util.List;

import javax.persistence.Tuple;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ITICS.Entity.Themes;

@Repository
public interface ThemesRepository extends JpaRepository<Themes, Long>{

	@Query(value="select Theme_Id from Themes where Theme_Name=?1",nativeQuery=true)
	public Integer getThemeIdByThemeName(String theme_name);
}
