package com.cjwx.titan.engine.reids.jwt;

import com.cjwx.titan.engine.core.base.bean.UserBean;
import com.cjwx.titan.engine.core.model.AbstractBox;
import lombok.Data;

/**
 * @Description: JWT TOKEN
 * @Author: qian li
 * @Date: 2018年03月31日 16:20
 */
@Data
public class JwtToken extends AbstractBox {

    private String tokenId;
    private String host;

    private UserBean user;

}
