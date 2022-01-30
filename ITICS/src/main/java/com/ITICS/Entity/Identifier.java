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
@Table(name="Identifier")
public class Identifier {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Identifier_Id")
	private Long identifier_id=(long)0;

	@Column(name="Identifier_Name")
	private String identifier_name="";
	
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

	public Long getIdentifier_id() {
		return identifier_id;
	}

	public void setIdentifier_id(Long identifier_id) {
		this.identifier_id = identifier_id;
	}

	public String getIdentifier_name() {
		return identifier_name;
	}

	public void setIdentifier_name(String identifier_name) {
		this.identifier_name = identifier_name;
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
		return "Identifier [identifier_id=" + identifier_id + ", identifier_name=" + identifier_name + ", valid="
				+ valid + ", created_by=" + created_by + ", create_date=" + create_date + ", modified_by=" + modified_by
				+ ", modified_date=" + modified_date + "]";
	}

	public Identifier(Long identifier_id, String identifier_name, String valid, String created_by, Date create_date,
			String modified_by, Date modified_date) {
		super();
		this.identifier_id = identifier_id;
		this.identifier_name = identifier_name;
		this.valid = valid;
		this.created_by = created_by;
		this.create_date = create_date;
		this.modified_by = modified_by;
		this.modified_date = modified_date;
	}

	public Identifier() {
		super();
		// TODO Auto-generated constructor stub
	}

}
