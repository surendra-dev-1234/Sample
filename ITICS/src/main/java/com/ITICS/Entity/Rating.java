package com.ITICS.Entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;

@Entity
@Table(name="Rating")
public class Rating {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Rating_Id")
	private Long rating_id=(long)0;

	@Column(name="Value")
	private String value="";
	
	@Column(name="Name")
	private String name="";
	
	@Column(name="Criteria")
	private String criteria="";

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

	public Long getRating_id() {
		return rating_id;
	}

	public void setRating_id(Long rating_id) {
		this.rating_id = rating_id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
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
		return "Rating [rating_id=" + rating_id + ", value=" + value + ", name=" + name + ", criteria=" + criteria
				+ ", valid=" + valid + ", created_by=" + created_by + ", create_date=" + create_date + ", modified_by="
				+ modified_by + ", modified_date=" + modified_date + "]";
	}

	public Rating(Long rating_id, String value, String name, String criteria, String valid, String created_by,
			Date create_date, String modified_by, Date modified_date) {
		super();
		this.rating_id = rating_id;
		this.value = value;
		this.name = name;
		this.criteria = criteria;
		this.valid = valid;
		this.created_by = created_by;
		this.create_date = create_date;
		this.modified_by = modified_by;
		this.modified_date = modified_date;
	}

	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
