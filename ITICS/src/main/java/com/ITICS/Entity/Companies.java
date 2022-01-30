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
@Table(name="Companies")
public class Companies {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Company_Id",nullable = false)	
	private Long company_id=(long)0;

	@Column(name="Company_Name",nullable = false)	
	private String company_name="";

	@Column(name="Description",nullable = true)	
	private String description="";
	
	@Column(name="Financial_Year",nullable = false)	
	private String financial_year="";
	
	@Column(name="Website",nullable = true)	
	private String website="";
	
	@Column(name="Investor_Relations",nullable = true)	
	private String investor_relations="";
	
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

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getInvestor_relations() {
		return investor_relations;
	}

	public void setInvestor_relations(String investor_relations) {
		this.investor_relations = investor_relations;
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
		return "Companies [company_id=" + company_id + ", company_name=" + company_name + ", description=" + description
				+ ", financial_year=" + financial_year + ", website=" + website + ", investor_relations="
				+ investor_relations + ", valid=" + valid + ", created_by=" + created_by + ", create_date="
				+ create_date + ", modified_by=" + modified_by + ", modified_date=" + modified_date + "]";
	}

	public Companies(Long company_id, String company_name, String description, String financial_year, String website,
			String investor_relations, String valid, String created_by, Date create_date, String modified_by,
			Date modified_date) {
		super();
		this.company_id = company_id;
		this.company_name = company_name;
		this.description = description;
		this.financial_year = financial_year;
		this.website = website;
		this.investor_relations = investor_relations;
		this.valid = valid;
		this.created_by = created_by;
		this.create_date = create_date;
		this.modified_by = modified_by;
		this.modified_date = modified_date;
	}

	public Companies() {
		super();
		// TODO Auto-generated constructor stub
	}

}
