package com.kanban.tmsh.service;

import javax.ejb.Remote;

import com.kanban.tmsh.domain.ColumnData;
import com.kanban.tmsh.entity.ColumnDataEntity;

@Remote
public interface IColumnDataDbEao {
	ColumnDataEntity addModifyColumnData(ColumnDataEntity cde);
	void modifyColumnData(ColumnData cd);
}
