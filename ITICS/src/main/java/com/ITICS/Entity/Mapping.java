package com.ITICS.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Mapping")
public class Mapping {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@Column(name="Theme_id")
	private Integer theme_id;
	
	@Column(name="Sector_Id")
	private Integer sector_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTheme_id() {
		return theme_id;
	}

	public void setTheme_id(Integer theme_id) {
		this.theme_id = theme_id;
	}

	public Integer getSector_id() {
		return sector_id;
	}

	public void setSector_id(Integer sector_id) {
		this.sector_id = sector_id;
	}

	@Override
	public String toString() {
		return "Mapping [id=" + id + ", theme_id=" + theme_id + ", sector_id=" + sector_id + "]";
	}

	public Mapping() {
		super();
		// TODO Auto-generated constructor stub
	}
}
