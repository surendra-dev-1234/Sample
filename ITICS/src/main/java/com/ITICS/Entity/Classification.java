package com.ITICS.Entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Classification")
public class Classification {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id",nullable = false)
	private Long id=(long)0;
	
	@Column(name="Company_Id",nullable = false)
	private Integer company_id=0;
	
	@Column(name="Year",nullable = false)
	private String year="";
	
	@Column(name="Theme_Id",nullable = false)
	private Integer theme_id=0;
	
	@Column(name="Revenue",nullable = false)
	private Double revenue=-1.0;
	
	@Column(name="Rating_Id",nullable = false)
	private Integer rating_id=0;
	
	@Column(name="Comments",nullable = true)
	private String comments="";
	
	@Column(name="Valid",nullable = false)
	private String valid="1";
	
	@Column(name="Created_By",nullable = false)
	private String created_by="";
	
	@Column(name="Create_Date",nullable = false)
	private Date create_date=new Timestamp(new Date().getTime());
	
	@Column(name="Modified_By",nullable = false)
	private String modified_by="";
	
	@Column(name="Modified_Date",nullable = true)
	private Date modified_date=null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getTheme_id() {
		return theme_id;
	}

	public void setTheme_id(Integer theme_id) {
		this.theme_id = theme_id;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public Integer getRating_id() {
		return rating_id;
	}

	public void setRating_id(Integer rating_id) {
		this.rating_id = rating_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	@Override
	public String toString() {
		return "Classification [id=" + id + ", company_id=" + company_id + ", year=" + year + ", theme_id=" + theme_id
				+ ", revenue=" + revenue + ", rating_id=" + rating_id + ", comments=" + comments + ", valid=" + valid
				+ ", created_by=" + created_by + ", create_date=" + create_date + ", modified_by=" + modified_by
				+ ", modified_date=" + modified_date + "]";
	}

	public Classification(Long id, Integer company_id, String year, Integer theme_id, Double revenue, Integer rating_id,
			String comments, String valid, String created_by, Date create_date, String modified_by,
			Date modified_date) {
		super();
		this.id = id;
		this.company_id = company_id;
		this.year = year;
		this.theme_id = theme_id;
		this.revenue = revenue;
		this.rating_id = rating_id;
		this.comments = comments;
		this.valid = valid;
		this.created_by = created_by;
		this.create_date = create_date;
		this.modified_by = modified_by;
		this.modified_date = modified_date;
	}

	public Classification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
