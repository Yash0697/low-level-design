package com.yash.design.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQL {
    
    private Map<String, List<Row>> db;
    public SQL(String[] names, int[] columns) {
        db = new HashMap<>();
        for(int i = 0; i < names.length; i++) {
            db.put(names[i], new ArrayList<Row>());
        }
    }

    void insertRow(String name, String[] row) {
        List<String> row1 = Arrays.asList(row);
        db.get(name).add(new Row(row1));
    }

    void deleteRow(String name, int rowId) {
        // cant delete ith entry of the List<List<String>> table, otherwise id of next rows will decrement by 1
        db.get(name).get(rowId - 1).isDeleted = true;
    }

    String selectCell(String name, int rowId, int columnId) {
        return db.get(name).get(rowId - 1).columnValues.get(columnId - 1);
    }
}


class Row {
    boolean isDeleted;
    List<String> columnValues;

    public Row(List<String> columnValues) {
        this.columnValues = columnValues;
    }
}