package com.wjq.af.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 动物种类 枚举类
 *
 * @author yixihan
 * @date 2023/2/20 16:39
 */
@Getter
@AllArgsConstructor
public enum AnimalTypeEnums {
    
    /**
     * 猫
     */
    CAT ("CAT", "猫"),
    
    /**
     * 大型犬
     */
    BIT_DOG ("BIT_DOG", "大型犬"),
    
    /**
     * 小型犬
     */
    SMALL_DOG ("SMALL_DOG", "小型犬"),
    
    /**
     * 兔子
     */
    RABBIT ("RABBIT", "兔子"),
    
    /**
     * 鸟类
     */
    BIRD ("BIRD", "鸟类"),
    
    /**
     * 其他
     */
    OTHER ("OTHER", "其他");
    
    
    private final String type;
    
    private final String description;
}
