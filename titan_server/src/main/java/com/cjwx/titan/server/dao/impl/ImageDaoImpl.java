package com.cjwx.titan.server.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.server.bean.SysImageBean;
import com.cjwx.titan.server.dao.ImageDao;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl extends BaseDao implements ImageDao {

    @Override
    public void createImage(SysImageBean image) {
        this.save(image);
    }

    @Override
    public int deleteImage(String hash) {
        return this.getExecute().table(SysImageBean.TABLE).eq("hash", hash).delete();
    }

}
