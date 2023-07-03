package com.kanban.tmsh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kanban.tmsh.domain.Row;
import com.kanban.tmsh.entity.ColumnEntity;
import com.kanban.tmsh.entity.KanbanEntity;
import com.kanban.tmsh.entity.RowEntity;

@Stateless
@LocalBean
public class RowDbEaoImpl implements IRowDbEao {


	@PersistenceContext(name = "KanbanPU")
	private EntityManager entityManager;
	
	@Override
	public List<Row> getKanbanRows(Integer kanbanId) {
    	List<Row> rowList = new ArrayList<>();
		List<RowEntity> rowEntityList = entityManager.createQuery("FROM RowEntity as re WHERE re.kanban.id = :id", RowEntity.class).setParameter("id", kanbanId).getResultList();
		rowList.addAll(rowEntityList.stream().map(Row::new).collect(Collectors.toList()));
		
		return rowList;
	}

	@Override
	public RowEntity addModifyRow(RowEntity row) {
		entityManager.merge(row);
		return row;
	}

}
