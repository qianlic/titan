package com.cjwx.titan.engine.core.base.dao;

import com.cjwx.titan.engine.util.DateUtils;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 抽象bean
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
@MappedSuperclass
public abstract class AbstractBean implements Serializable {

    private static final long serialVersionUID = 3587850931125773471L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "status")
    private Boolean status = true;
    @Column(name = "ts")
    protected Date ts = DateUtils.now();

    @Override
    public String toString() {
        return "{createTime=" + ts + "}";
    }

}
