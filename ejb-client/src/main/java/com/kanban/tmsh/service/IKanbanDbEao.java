package com.kanban.tmsh.service;

import java.util.List;

import javax.ejb.Remote;

import com.kanban.tmsh.domain.Kanban;
import com.kanban.tmsh.entity.KanbanEntity;

@Remote
public interface IKanbanDbEao {
	KanbanEntity addKanban(Kanban kanban);
	KanbanEntity getKbEntity(Integer id);
	List<Kanban> getKanbans();
	Kanban getKanban(Integer id);

}
