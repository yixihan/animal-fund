package com.wjq.af.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 留言类型 枚举类
 *
 * @author yixihan
 * @date 2023/2/21 16:32
 */
@Getter
@AllArgsConstructor
public enum CommentTypeEnums {
    
    /**
     * 留言
     */
    ROOT ("ROOT"),
    
    /**
     * 留言回复
     */
    REPLY ("REPLY");
    
    private final String type;
}
