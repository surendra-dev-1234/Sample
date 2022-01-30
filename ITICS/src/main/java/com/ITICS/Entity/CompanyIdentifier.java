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
@Table(name="companyidentifier")
public class CompanyIdentifier {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id=(long)0;

	@Column(name="Company_Id")
	private Integer company_id=0;

	@Column(name="Identifier_Name")
	private String identifier_name="";

	@Column(name="Identifier_Value")
	private String identifier_value="";
	
	@Column(name="Valid")
	private String valid="1";
	
	@Column(name="Created_By")
	private String created_by="";

	@Column(name="Create_Date")
	private Date create_date=new Timestamp(new Date().getTime());

	@Column(name="Modified_By")
	private String modified_by="";

	@Column(name="Modified_Date")
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

	public String getIdentifier_name() {
		return identifier_name;
	}

	public void setIdentifier_name(String identifier_name) {
		this.identifier_name = identifier_name;
	}

	public String getIdentifier_value() {
		return identifier_value;
	}

	public void setIdentifier_value(String identifier_value) {
		this.identifier_value = identifier_value;
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
		return "CompanyIdentifier [id=" + id + ", company_id=" + company_id + ", identifier_name=" + identifier_name
				+ ", identifier_value=" + identifier_value + ", created_by=" + created_by + ", create_date="
				+ create_date + ", modified_by=" + modified_by + ", modified_date=" + modified_date + "]";
	}

	public CompanyIdentifier(Long id, Integer company_id, String identifier_name, String identifier_value,
			String created_by, Date create_date, String modified_by, Date modified_date) {
		super();
		this.id = id;
		this.company_id = company_id;
		this.identifier_name = identifier_name;
		this.identifier_value = identifier_value;
		this.created_by = created_by;
		this.create_date = create_date;
		this.modified_by = modified_by;
		this.modified_date = modified_date;
	}

	public CompanyIdentifier() {
		super();
		// TODO Auto-generated constructor stub
	}
}
