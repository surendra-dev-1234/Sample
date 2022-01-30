package com.ITICS.Repository;

import java.util.List;

import javax.persistence.Tuple;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ITICS.Entity.Classification;
import com.ITICS.Entity.Rating;
import com.ITICS.Entity.Themes;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{

	@Query(value="select Rating_Id from Rating where name=?1",nativeQuery=true)
	public Integer getRating_IdbyRating_nameFromRating(String rating_name);
}
