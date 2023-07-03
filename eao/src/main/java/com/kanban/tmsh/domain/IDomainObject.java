package com.kanban.tmsh.domain;

public interface IDomainObject extends Comparable {

    Integer getId();

    default int compareTo(Object o) {
        return 0;
    }
}

