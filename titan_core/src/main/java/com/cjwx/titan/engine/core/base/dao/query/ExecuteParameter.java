package com.cjwx.titan.engine.core.base.dao.query;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年04月26日 19:10
 */
@Data
public class ExecuteParameter extends SqlParameter {

    private String table;
    private List<String> setList = new ArrayList<>();

    public void table(String table) {
        this.table = table;
    }

    public void set(String set) {
        this.setList.add(set);
    }

}
