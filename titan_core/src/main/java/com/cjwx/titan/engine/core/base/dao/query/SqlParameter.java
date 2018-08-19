package com.cjwx.titan.engine.core.base.dao.query;

import com.cjwx.titan.engine.util.StringUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SQL参数
 * @Author: qian li
 * @Date: 2018年04月03日 10:54
 */
@Data
public class SqlParameter {

    private String sql;
    private List<String> whereList = new ArrayList<>();
    private List<Object> argList = new ArrayList<>();

    public void where(SqlCondition condition) {
        if (whereList.size() == 0){
            condition.setLinkIdentifier(StringUtils.NULL_STRING);
        }
        where(condition.toString());
        arg(condition.getValue());
    }

    public void where(String where) {
        this.whereList.add(where);
    }

    public void arg(Object arg) {
        this.argList.add(arg);
    }

    public <T> void args(List<T> args) {
        this.argList.addAll(args);
    }

    public Object[] getArgs(){
        return this.argList.toArray();
    }

    protected void clear() {
        this.sql = null;
        this.whereList.clear();
        this.argList.clear();
    }

}
