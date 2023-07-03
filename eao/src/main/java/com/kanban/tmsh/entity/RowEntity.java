package com.kanban.tmsh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rows", schema = "public")
public class RowEntity implements Serializable {
	private Integer id;
	private Integer rowNm;
	private KanbanEntity kanban;
	private List<ColumnDataEntity> columnDataList;
	
	public RowEntity() {}

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
    @javax.persistence.Column(name = "row_nm", nullable = false)
	public Integer getRowNm() {
		return rowNm;
	}

	public void setRowNm(Integer rowNm) {
		this.rowNm = rowNm;
	}
	
	@ManyToOne
    @JoinColumn(name = "kanban_id")
	public KanbanEntity getKanban() {
		return kanban;
	}

	public void setKanban(KanbanEntity kanban) {
		this.kanban = kanban;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "column_data_row", joinColumns = @JoinColumn(name = "row_id"), inverseJoinColumns = @JoinColumn(name = "column_data_id"))
	public List<ColumnDataEntity> getColumnDataList() {
		return columnDataList;
	}

	public void setColumnDataList(List<ColumnDataEntity> columnDataList) {
		this.columnDataList = columnDataList;
	}
	
}
