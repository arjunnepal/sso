package com.custom.spring.sso.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResult<T> {
    private Long totalElements;
    private int pageSize;
    private int pageNo;
    private List<T> data;

    public PageResult(Long totalElements,
                      int pageSize,
                      int pageNo,
                      List<T> data) {

        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.data = data;

    }
}