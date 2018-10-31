package com.cjwx.titan.engine.core.base.dao.query;

import com.cjwx.titan.engine.util.StringUtils;
import lombok.Data;

/**
 * @Description: 数据库查询关系
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public class SqlCondition {

    private String linkIdentifier;
    private String column;
    private String condition;
    private Object value;

    public SqlCondition(String linkIdentifier, String column, String condition, Object value) {
        this.linkIdentifier = linkIdentifier;
        this.column = column;
        this.condition = condition;
        this.value = value;
    }

    public SqlCondition(String column, String condition, Object value) {
        this(CONDITION.AND, column, condition, value);
    }

    public SqlCondition(String column, Object value) {
        this(column, CONDITION.EQ, value);
    }

    public String toString() {
        String result = this.column + StringUtils.SPACE_STRING + this.condition;
        if (StringUtils.isNotEmpty(this.linkIdentifier)) {
            result = this.linkIdentifier + StringUtils.SPACE_STRING + result;
        }
        if (CONDITION.LIKE.equalsIgnoreCase(this.condition)) {
            return result + " %?% ";
        }
        return result + " ? ";
    }

    public class CONDITION {
        public static final String AND = " AND ";
        public static final String OR = " OR ";

        public static final String EQ = " = ";
        public static final String GT = " > ";
        public static final String LT = " < ";
        public static final String GTEQ = " >= ";
        public static final String LTEQ = " <= ";
        public static final String UNEQUAL = " <> ";
        public static final String LIKE = " LIKE ";
        public static final String IN = " IN ";
    }

}
