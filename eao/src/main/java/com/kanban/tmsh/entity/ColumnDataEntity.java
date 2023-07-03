package com.kanban.tmsh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "column_data", schema = "public")
public class ColumnDataEntity implements Serializable{
	private Integer id; 
	private ColumnEntity column;
	private String data;
	
	public ColumnDataEntity() {}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Basic
    @javax.persistence.Column(name = "data", length = 100)
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@ManyToOne
    @JoinColumn(name = "column_id")
	public ColumnEntity getColumn() {
		return column;
	}

	public void setColumn(ColumnEntity column) {
		this.column = column;
	}	
}
