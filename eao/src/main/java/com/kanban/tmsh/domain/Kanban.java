package com.kanban.tmsh.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.kanban.tmsh.entity.KanbanEntity;

public class Kanban implements Serializable, IDomainObject{
	private Integer id;
	private String name;
	private List<Column> columnList;
	
	public Kanban() {}
	
	public Kanban(Integer id, String name, List<Column> columnList) {
		this.id = id;
		this.name = name;
		this.columnList = columnList;
	}
	
	public Kanban(KanbanEntity ke) {
		this.id = ke.getId();
		this.name = ke.getName();
		this.columnList = ke.getColumns().stream().map(Column::new).collect(Collectors.toList());
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

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}
}
