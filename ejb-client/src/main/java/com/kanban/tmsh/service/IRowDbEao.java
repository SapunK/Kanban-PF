package com.kanban.tmsh.service;

import java.util.List;
import javax.ejb.Remote;
import com.kanban.tmsh.domain.Row;
import com.kanban.tmsh.entity.RowEntity;

@Remote
public interface IRowDbEao {
	List<Row> getKanbanRows(Integer kanbanId);
	RowEntity addModifyRow(RowEntity row);
}
