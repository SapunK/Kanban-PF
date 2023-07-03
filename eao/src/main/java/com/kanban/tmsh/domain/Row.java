package com.kanban.tmsh.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.kanban.tmsh.entity.RowEntity;

public class Row implements Serializable {
	private Integer id;
	private Integer rowNm;
	private Kanban kanban;
	private List<ColumnData> cdList;
	
	public Row() {}
	
	public Row(RowEntity re) {
		this.id = re.getId();
		this.rowNm = re.getRowNm();
		this.kanban = new Kanban(re.getKanban());
		this.cdList = re.getColumnDataList().stream().map(ColumnData::new).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRowNm() {
		return rowNm;
	}

	public void setRowNm(Integer rowNm) {
		this.rowNm = rowNm;
	}
	
	public Kanban getKanban() {
		return kanban;
	}

	public void setKanban(Kanban kanban) {
		this.kanban = kanban;
	}

	public List<ColumnData> getCdList() {
		return cdList;
	}

	public void setCdList(List<ColumnData> cdList) {
		this.cdList = cdList;
	}
}
