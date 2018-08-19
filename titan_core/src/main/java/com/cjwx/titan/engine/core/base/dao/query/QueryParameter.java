package com.cjwx.titan.engine.core.base.dao.query;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @Description: 数据库查询参数
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public class QueryParameter extends SqlParameter {

    private List<String> selectList = new ArrayList<>();
    private List<String> fromList = new ArrayList<>();
    private List<String> groupByList = new ArrayList<>();
    private List<String> orderByList = new ArrayList<>();

    public void select(String select) {
        selectList.add(select);
    }

    public void from(String from) {
        this.fromList.add(from);
    }

    public void groupBy(String groupBy){
        groupByList.add(groupBy);
    }

    public void orderBy(String orderBy){
        groupByList.add(orderBy);
    }

    protected void clear() {
        super.clear();
        this.selectList.clear();
        this.fromList.clear();
        this.groupByList.clear();
        this.orderByList.clear();
    }

}
