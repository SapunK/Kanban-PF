package com.kanban.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kanban.tmsh.domain.Column;
import com.kanban.tmsh.domain.ColumnData;
import com.kanban.tmsh.domain.Kanban;
import com.kanban.tmsh.domain.Row;
import com.kanban.tmsh.entity.ColumnDataEntity;
import com.kanban.tmsh.entity.ColumnEntity;
import com.kanban.tmsh.entity.KanbanEntity;
import com.kanban.tmsh.entity.RowEntity;
import com.kanban.tmsh.service.IColumnDataDbEao;
import com.kanban.tmsh.service.IKanbanDbEao;
import com.kanban.tmsh.service.IRowDbEao;

@ManagedBean(name = "useKanbanView")
@ViewScoped
public class UseKanbanView implements Serializable{

	private Kanban kanban;
	private List<Row> rowList;
	private String task;

	@EJB
	private IKanbanDbEao kanbanDbEao;

	@EJB
	private IRowDbEao rowDbEao;

	@EJB
	private IColumnDataDbEao columnDataDbEao;

	@PostConstruct
	public void init(){
		kanban = new Kanban();

		Integer kanbanId = null;

		try {
			kanbanId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("kanbanId"));
			kanban = kanbanDbEao.getKanban(kanbanId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		rowList = rowDbEao.getKanbanRows(kanbanId);
	}

	public Kanban getKanban() {
		return kanban;
	}

	public void setKanban(Kanban kanban) {
		this.kanban = kanban;
	}

	public List<Row> getRowList() {
		return rowList;
	}

	public void setRowList(List<Row> rowList) {
		this.rowList = rowList;
	}

	public String getColData(Column col, Row row) {
		for(ColumnData cd : row.getCdList()) {
			if(cd.getColumn().getId().equals(col.getId()))
				return cd.getData();
		}
		return "";
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public void addNewTask() {
		RowEntity re = new RowEntity();
		KanbanEntity ke = kanbanDbEao.getKbEntity(kanban.getId()); 
		re.setKanban(ke);
		re.setRowNm(rowList.size() + 1);
		
		if(rowList.size() == 1) {
			Boolean isRowEmpty = true;
			for(ColumnData cd : rowList.get(0).getCdList()) {
				if(!cd.getData().isEmpty()) {
					isRowEmpty = false;
					break;
				}
			}
			if(isRowEmpty) {
				re.setRowNm(1);
				re.setId(rowList.get(0).getId());
			}
		}

		List<ColumnDataEntity> cdeList = new ArrayList<>();
		String taskData = task;
		
		for(ColumnEntity c : ke.getColumns()) {
			ColumnDataEntity cde = new ColumnDataEntity();
			cde.setColumn(c);
			cde.setData(taskData);
			cde = columnDataDbEao.addModifyColumnData(cde);
			cdeList.add(cde);
			taskData = "";
		}

		re.setColumnDataList(cdeList);

		rowDbEao.addModifyRow(re);
		
		rowList = rowDbEao.getKanbanRows(ke.getId());
	}
	
	public void shiftDataRight(Row row) {
		List<ColumnData> cdList = row.getCdList();
		Collections.sort(cdList, new Comparator<ColumnData>() {
			public int compare(ColumnData cd1, ColumnData cd2) {
				return cd1.getId().compareTo(cd2.getId());
			}
		});
		
		for(int i = 0 ; i <= cdList.size() - 1 ; i++) {
			if(!cdList.get(i).getData().isEmpty() && cdList.size() != i + 1) {
				ColumnData currentCd = cdList.get(i);
				ColumnData nextCd = cdList.get(i + 1);
				nextCd.setData(currentCd.getData());
				currentCd.setData("");
				columnDataDbEao.modifyColumnData(currentCd);
				columnDataDbEao.modifyColumnData(nextCd);
				break;
			}
		}
		rowList = rowDbEao.getKanbanRows(kanban.getId());		
	}
		
	public void shiftDataLeft(Row row) {
		List<ColumnData> cdList = row.getCdList();
		Collections.sort(cdList, new Comparator<ColumnData>() {
			public int compare(ColumnData cd1, ColumnData cd2) {
				return cd1.getId().compareTo(cd2.getId());
			}
		});
		
		for(int i = 0 ; i <= cdList.size() - 1 ; i++) {
			if(!cdList.get(i).getData().isEmpty() && i > 0) {
				ColumnData currentCd = cdList.get(i);
				ColumnData prevCd = cdList.get(i - 1);
				prevCd.setData(currentCd.getData());
				currentCd.setData("");
				columnDataDbEao.modifyColumnData(currentCd);
				columnDataDbEao.modifyColumnData(prevCd);
				break;
			}
		}
		rowList = rowDbEao.getKanbanRows(kanban.getId());
	}
}
