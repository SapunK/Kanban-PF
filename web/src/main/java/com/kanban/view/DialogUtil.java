package com.kanban.view;

import org.primefaces.PrimeFaces;

import com.kanban.tmsh.domain.IDomainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DialogUtil<T> implements Serializable {

    private T selectedItem;

    public DialogUtil() {
    }

    public void openDialog(String fileName, Integer width, Integer height) {
        Map<String, Object> options = new HashMap<>();
        Map<String, List<String>> parameterMap = new HashMap<>();

        List<String> params = new ArrayList<>();
        if (selectedItem != null){
            params.add(String.valueOf(((IDomainObject)selectedItem).getId()));
            parameterMap.put("id", params);
        }

        options.put("modal", true);
        options.put("resizable", false);
        options.put("width", width);
        options.put("height", height);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");

        PrimeFaces.current().dialog().openDynamic(fileName, options, parameterMap);
    }

    public T getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(T selectedItem) {
        this.selectedItem = selectedItem;
    }

}
