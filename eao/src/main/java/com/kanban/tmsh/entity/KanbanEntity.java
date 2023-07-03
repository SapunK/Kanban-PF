package com.kanban.tmsh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "kanban", schema = "public")
public class KanbanEntity implements Serializable{
	private Integer id;
	private String name;
	private List<ColumnEntity> columns;
	
	public KanbanEntity() {}
	
	public KanbanEntity(Integer id, String name, List<ColumnEntity> columns) {
		this.id = id;
		this.name = name;
		this.columns = columns;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Basic
    @Column(name = "name", nullable = false, length = 100, unique = true)
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "kanban_column", joinColumns = @JoinColumn(name = "kanban_id"), inverseJoinColumns = @JoinColumn(name = "column_id"))
	public List<ColumnEntity> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnEntity> columns) {
		this.columns = columns;
	}
}
