package com.kasarisute.json;

import java.util.List;

import lombok.Data;

@Data
public class ResponseListEntry<T> {
    protected List<T> list;
    protected int pageNo;
    protected int pageSize;
    protected double total;
    protected int totalPage;
    protected boolean hasNext;
}
