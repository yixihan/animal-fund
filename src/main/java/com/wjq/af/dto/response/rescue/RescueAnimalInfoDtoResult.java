package com.wjq.af.dto.response.rescue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 搜索救援动物信息-dtoResult
 *
 * @author yixihan
 * @date 2023/2/20 16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("搜索救援动物信息-dtoResult")
public class RescueAnimalInfoDtoResult {
    
    @ApiModelProperty(value = "主键 ID")
    private Long id;
    
    @ApiModelProperty(value = "申请用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "动物种类[猫: CAT, 大型犬: BIT_DOG, 小型犬: SMALL_DOG, 兔子: RABBIT, 鸟类: BIRD, 其他: OTHER]")
    private String animalType;
    
    @ApiModelProperty(value = "动物性别[雄性:MALE, 雌性:FEMALE]")
    private String animalGender;
    
    @ApiModelProperty(value = "受伤程度[饥饿: HUNGER, 外伤(较轻): TRAUMA_LIGHTER, 外伤(较重): TRAUMA_SERIOUS, 怀孕: PREGNANT, 绝育: STERILIZATION, 狂犬病: RABIES, 气管炎: TRACHEITIS, 口腔炎: STOMATITIS, 皮肤病: SKIN_DISEASE, 犬瘟: CANINE_DISTEMPER, 其他: OTHER]")
    private String injuryDegree;
    
    @ApiModelProperty(value = "地点")
    private String address;
    
    @ApiModelProperty(value = "具体地点")
    private String addressDetail;
    
    @ApiModelProperty(value = "申请原因")
    private String applyReason;
    
    @ApiModelProperty(value = "申请照片")
    private String applyImg;
    
    @ApiModelProperty(value = "审核状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]")
    private String examineStatus;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
