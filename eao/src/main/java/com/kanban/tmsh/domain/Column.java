package com.kanban.tmsh.domain;

import java.io.Serializable;

import com.kanban.tmsh.entity.ColumnEntity;

public class Column implements Serializable{
	private Integer id;
	private String name;
	
	public Column() {}
	
	public Column(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Column(ColumnEntity ce) {
		this.id = ce.getId();
		this.name = ce.getName();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
