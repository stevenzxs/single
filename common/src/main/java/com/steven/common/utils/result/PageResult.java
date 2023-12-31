package com.steven.common.utils.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult implements Serializable {
    /*
     * 总记录数
     * */
    private Long total;
    /*
     * 当前页结果
     * */
    private List rows;
}

