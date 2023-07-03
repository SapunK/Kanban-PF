package com.kanban.tmsh.domain;

import java.io.Serializable;

import com.kanban.tmsh.entity.ColumnDataEntity;

public class ColumnData implements Serializable{
	private Integer id;
	private Column column;
	private String data;

	public ColumnData(Integer id, Column column, String data) {
		this.id = id;
		this.column = column;
		this.data = data;
	}

	public ColumnData() {}

	public ColumnData(ColumnDataEntity cde) {
		this.id = cde.getId();
		this.column = new Column(cde.getColumn());
		this.data = cde.getData();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
