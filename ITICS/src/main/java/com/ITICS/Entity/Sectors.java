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
@Table(name="Sectors")
public class Sectors {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sector_Id",nullable = false)	
	private Long sector_id=(long)0;
	
	@Column(name="Sector_Name",nullable = false)
	private String sector_name="";
	
	@Column(name="Description",nullable = true)
	private String description="";
	
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

	public Long getSector_id() {
		return sector_id;
	}

	public void setSector_id(Long sector_id) {
		this.sector_id = sector_id;
	}

	public String getSector_name() {
		return sector_name;
	}

	public void setSector_name(String sector_name) {
		this.sector_name = sector_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "Sectors [sector_id=" + sector_id + ", sector_name=" + sector_name + ", description=" + description
				+ ", valid=" + valid + ", created_by=" + created_by + ", create_date=" + create_date + ", modified_by="
				+ modified_by + ", modified_date=" + modified_date + "]";
	}

	public Sectors(Long sector_id, String sector_name, String description, String valid, String created_by,
			Date create_date, String modified_by, Date modified_date) {
		super();
		this.sector_id = sector_id;
		this.sector_name = sector_name;
		this.description = description;
		this.valid = valid;
		this.created_by = created_by;
		this.create_date = create_date;
		this.modified_by = modified_by;
		this.modified_date = modified_date;
	}

	public Sectors() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
