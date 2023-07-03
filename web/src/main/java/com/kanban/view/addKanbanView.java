package com.kanban.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.kanban.tmsh.domain.Column;
import com.kanban.tmsh.domain.Kanban;
import com.kanban.tmsh.entity.ColumnDataEntity;
import com.kanban.tmsh.entity.ColumnEntity;
import com.kanban.tmsh.entity.KanbanEntity;
import com.kanban.tmsh.entity.RowEntity;
import com.kanban.tmsh.service.IColumnDataDbEao;
import com.kanban.tmsh.service.IKanbanDbEao;
import com.kanban.tmsh.service.IRowDbEao;

@ManagedBean(name = "addKanbanView")
@ViewScoped
public class addKanbanView implements Serializable{
	private Kanban kanban;
	private String kanbanName;
	private String newColumnName;
	private List<Column> columnList;
	
	@EJB
    private IKanbanDbEao kanbanDbEao;
	
	@EJB
	private IRowDbEao rowDbEao;
	
	@EJB
	private IColumnDataDbEao columnDataDbEao;
	
	@PostConstruct
	public void init() {
		Map<String, String> parameterMap;
        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        parameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        columnList = new ArrayList<>();
        kanban = new Kanban();

	}
	
	public void addColumn() {
		Column col = new Column();
		col.setName(newColumnName);
		columnList.add(col);
		kanban.setColumnList(columnList);
	}

	public Kanban getKanban() {
		return kanban;
	}

	public void setKanban(Kanban kanban) {
		this.kanban = kanban;
	}

	public void addKanban() {
		KanbanEntity ke = kanbanDbEao.addKanban(kanban);
		
		RowEntity re = new RowEntity();
		re.setKanban(ke);
		re.setRowNm(1);
		
		List<ColumnDataEntity> cdeList = new ArrayList<>();
		
		for(ColumnEntity c : ke.getColumns()) {
			ColumnDataEntity cde = new ColumnDataEntity();
			cde.setColumn(c);
			cde.setData("");
			cde = columnDataDbEao.addModifyColumnData(cde);
			cdeList.add(cde);
		}
		
		re.setColumnDataList(cdeList);
		re = rowDbEao.addModifyRow(re);
		PrimeFaces.current().dialog().closeDynamic(null);
	}


	public String getNewColumnName() {
		return newColumnName;
	}


	public void setNewColumnName(String newColumnName) {
		this.newColumnName = newColumnName;
	}

	public String getKanbanName() {
		return kanbanName;
	}

	public void setKanbanName(String kanbanName) {
		this.kanbanName = kanbanName;
		kanban.setName(kanbanName);
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}
}
