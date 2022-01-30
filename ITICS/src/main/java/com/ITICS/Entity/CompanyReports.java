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
@Table(name="companyreports")
public class CompanyReports {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id",nullable = false)	
	private Long id=(long)0;

	@Column(name="Company_Id",nullable = false)	
	private Integer company_id=0;

	@Column(name="Year",nullable = false)	
	private String year="";

	@Column(name="Location",nullable = true)	
	private String location="";

	@Column(name="Comments",nullable = true)	
	private String comments="";
	
	@Column(name="QC_Status",nullable = true)	
	private String qc_status="";
	
	@Column(name="QC_By",nullable = true)	
	private String qc_by="";
	
	@Column(name="Valid",nullable = false)	
	private String valid="";
	
	@Column(name="Created_By",nullable = false)	
	private String created_by="";
	
	@Column(name="Create_Date",nullable = false)	
	private Date create_date=new Timestamp(new Date().getTime());
	
	@Column(name="Modified_By",nullable = false)	
	private String modified_by="";
	
	@Column(name="Modified_Date",nullable = true)	
	private Date modified_date=null;
	
	@Column(name="Other_Sector",nullable = true)	
	private String other_sector="";

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getQc_status() {
		return qc_status;
	}

	public void setQc_status(String qc_status) {
		this.qc_status = qc_status;
	}

	public String getQc_by() {
		return qc_by;
	}

	public void setQc_by(String qc_by) {
		this.qc_by = qc_by;
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

	public String getOther_sector() {
		return other_sector;
	}

	public void setOther_sector(String other_sector) {
		this.other_sector = other_sector;
	}

	@Override
	public String toString() {
		return "CompanyReports [id=" + id + ", company_id=" + company_id + ", year=" + year + ", location=" + location
				+ ", comments=" + comments + ", qc_status=" + qc_status + ", qc_by=" + qc_by + ", valid=" + valid
				+ ", created_by=" + created_by + ", create_date=" + create_date + ", modified_by=" + modified_by
				+ ", modified_date=" + modified_date + ", other_sector=" + other_sector + "]";
	}

	public CompanyReports(Long id, Integer company_id, String year, String location, String comments, String qc_status,
			String qc_by, String valid, String created_by, Date create_date, String modified_by, Date modified_date,
			String other_sector) {
		super();
		this.id = id;
		this.company_id = company_id;
		this.year = year;
		this.location = location;
		this.comments = comments;
		this.qc_status = qc_status;
		this.qc_by = qc_by;
		this.valid = valid;
		this.created_by = created_by;
		this.create_date = create_date;
		this.modified_by = modified_by;
		this.modified_date = modified_date;
		this.other_sector = other_sector;
	}

	public CompanyReports() {
		super();
		// TODO Auto-generated constructor stub
	}
}
