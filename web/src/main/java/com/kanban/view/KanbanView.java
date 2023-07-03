package com.kanban.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.kanban.tmsh.domain.Kanban;
import com.kanban.tmsh.service.IKanbanDbEao;

@ManagedBean(name = "kanbanView")
@ViewScoped
public class KanbanView implements Serializable{
	private DialogUtil dialogUtil;
	private List<Kanban> kanbanList;
	
	@EJB
	private IKanbanDbEao kanbanDbEao;
	
	@PostConstruct
	public void init() {
		dialogUtil = new DialogUtil<Kanban>();
		kanbanList = kanbanDbEao.getKanbans();
	}

	public DialogUtil getDialogUtil() {
		return dialogUtil;
	}

	public void setDialogUtil(DialogUtil dialogUtil) {
		this.dialogUtil = dialogUtil;
	}

	public List<Kanban> getKanbanList() {
		return kanbanList;
	}

	public void setKanbanList(List<Kanban> kanbanList) {
		this.kanbanList = kanbanList;
	}
	
	public void useKanban(Integer id) {
		Kanban selectedItem = new Kanban();
		for(Kanban k : kanbanList) {
			if(k.getId().equals(id)) {
				selectedItem = k;
				break;
			}
		}
		
		dialogUtil.setSelectedItem(selectedItem);
		dialogUtil.openDialog("useKanban", 300, 500);
	}
	
	public void updateList() {
		kanbanList = kanbanDbEao.getKanbans();
	}
}
