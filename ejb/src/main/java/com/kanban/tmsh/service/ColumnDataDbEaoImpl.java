package com.kanban.tmsh.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kanban.tmsh.domain.ColumnData;
import com.kanban.tmsh.entity.ColumnDataEntity;

@Stateless
@LocalBean
public class ColumnDataDbEaoImpl implements IColumnDataDbEao{

	@PersistenceContext(name = "KanbanPU")
	private EntityManager entityManager;
	
	@Override
	public ColumnDataEntity addModifyColumnData(ColumnDataEntity cde) {
		entityManager.persist(cde);
		return cde;
	}
	
	@Override
	public void modifyColumnData(ColumnData cd) {
		ColumnDataEntity cde = entityManager.find(ColumnDataEntity.class, cd.getId());
		cde.setData(cd.getData());
		entityManager.persist(cde);
	}

}
