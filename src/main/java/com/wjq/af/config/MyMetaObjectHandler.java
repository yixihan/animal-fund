package com.wjq.af.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MP 自动插入
 *
 * @author yixihan
 * @date 2023/2/17 14:20
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill (metaObject, "createTime", Date.class, new Date ());
        this.strictInsertFill (metaObject, "updateTime", Date.class, new Date ());
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill (metaObject, "updateTime", Date.class, new Date ());
    }
}
