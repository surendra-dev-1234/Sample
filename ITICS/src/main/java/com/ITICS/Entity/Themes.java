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
@Table(name="Themes")
public class Themes {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Theme_Id",nullable = false)
	private Long theme_id=(long)0;

	@Column(name="Theme_Name",nullable = false)
	private String theme_name="";

	@Column(name="Description",nullable = true)
	private String description="";

	@Column(name="Key_Words",nullable = true)
	private String key_words="";
	
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

	public Long getTheme_id() {
		return theme_id;
	}

	public void setTheme_id(Long theme_id) {
		this.theme_id = theme_id;
	}

	public String getTheme_name() {
		return theme_name;
	}

	public void setTheme_name(String theme_name) {
		this.theme_name = theme_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey_words() {
		return key_words;
	}

	public void setKey_words(String key_words) {
		this.key_words = key_words;
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
		return "Themes [theme_id=" + theme_id + ", theme_name=" + theme_name + ", description=" + description
				+ ", key_words=" + key_words + ", valid=" + valid + ", created_by=" + created_by + ", create_date="
				+ create_date + ", modified_by=" + modified_by + ", modified_date=" + modified_date + "]";
	}

	public Themes(Long theme_id, String theme_name, String description, String key_words, String valid,
			String created_by, Date create_date, String modified_by, Date modified_date) {
		super();
		this.theme_id = theme_id;
		this.theme_name = theme_name;
		this.description = description;
		this.key_words = key_words;
		this.valid = valid;
		this.created_by = created_by;
		this.create_date = create_date;
		this.modified_by = modified_by;
		this.modified_date = modified_date;
	}

	public Themes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
