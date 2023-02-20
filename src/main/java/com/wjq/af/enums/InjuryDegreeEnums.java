package com.wjq.af.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 受伤程度 枚举类
 *
 * @author yixihan
 * @date 2023/2/20 16:45
 */
@Getter
@AllArgsConstructor
public enum InjuryDegreeEnums {
    
    /**
     * 饥饿
     */
    HUNGER ("HUNGER", "饥饿"),
    
    /**
     * 外伤 (较轻)
     */
    TRAUMA_LIGHTER ("TRAUMA_LIGHTER", "外伤 (较轻)"),
    
    /**
     * 外伤 (较重)
     */
    TRAUMA_SERIOUS ("TRAUMA_SERIOUS", "外伤 (较重)"),
    
    /**
     * 怀孕
     */
    PREGNANT ("PREGNANT", "怀孕"),
    
    /**
     * 绝育
     */
    STERILIZATION ("STERILIZATION", "绝育"),
    
    /**
     * 狂犬病
     */
    RABIES ("RABIES", "狂犬病"),
    
    /**
     * 气管炎
     */
    TRACHEITIS ("TRACHEITIS", "气管炎"),
    
    /**
     * 口腔炎
     */
    STOMATITIS ("STOMATITIS", "口腔炎"),
    
    /**
     * 皮肤病
     */
    SKIN_DISEASE ("SKIN_DISEASE", "皮肤病"),
    
    /**
     * 犬瘟
     */
    CANINE_DISTEMPER ("CANINE_DISTEMPER", "犬瘟"),
    
    /**
     * 其他
     */
    OTHER ("OTHER", "其他");
    
    
    private final String value;
    
    private final String description;
}
