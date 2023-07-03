package com.kanban.tmsh.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.kanban.tmsh.domain.Kanban;
import com.kanban.tmsh.entity.ColumnEntity;
import com.kanban.tmsh.entity.KanbanEntity;

@Stateless
@LocalBean
public class KanbanDbEaoImpl implements IKanbanDbEao{

	@PersistenceContext(name = "KanbanPU")
	private EntityManager entityManager;
	
	@Override
	public KanbanEntity addKanban(Kanban kanban) {
		KanbanEntity ke = new KanbanEntity();
		ke.setName(kanban.getName());
		ke.setColumns(kanban.getColumnList().stream().map(ColumnEntity::new).collect(Collectors.toList()));
		
		entityManager.persist(ke);
		return ke;
	}

	@Override
	public List<Kanban> getKanbans() {
		List<Kanban> kanbanList = null;

		kanbanList = entityManager.createQuery("FROM KanbanEntity as ke", KanbanEntity.class).getResultList().stream().map(Kanban::new).collect(Collectors.toList());
 
		return kanbanList;
	}

	@Override
	public Kanban getKanban(Integer id) {
		Kanban kb = null;
		KanbanEntity ke = entityManager.createQuery("FROM KanbanEntity as ke WHERE ke.id = :id", KanbanEntity.class).setParameter("id", id).getSingleResult();
		kb = new Kanban(ke);
		
		return kb;
	}

	@Override
	public KanbanEntity getKbEntity(Integer id) {
		return entityManager.createQuery("FROM KanbanEntity as ke WHERE ke.id = :id", KanbanEntity.class).setParameter("id", id).getSingleResult();
	}
}
