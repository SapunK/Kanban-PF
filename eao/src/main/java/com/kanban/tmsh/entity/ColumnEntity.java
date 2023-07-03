package com.kanban.tmsh.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kanban.tmsh.domain.Column;

@Entity
@Table(name = "columns", schema = "public")
public class ColumnEntity implements Serializable{
	private Integer id;
	private String name;
	
	public ColumnEntity() {}
	
	public ColumnEntity(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ColumnEntity(Column column) {
		this.id = column.getId();
		this.name = column.getName();
	}
	
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
    @javax.persistence.Column(name = "name", nullable = false, length = 40)
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
