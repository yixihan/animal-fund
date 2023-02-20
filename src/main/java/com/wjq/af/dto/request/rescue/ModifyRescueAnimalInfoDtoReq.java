package com.wjq.af.dto.request.rescue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 修改救援动物信息-dtoReq
 *
 * @author yixihan
 * @date 2023/2/20 16:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改救援动物信息-dtoReq")
public class ModifyRescueAnimalInfoDtoReq {
    
    @ApiModelProperty(value = "主键 ID")
    private Long id;
    
    @ApiModelProperty(value = "动物种类[猫: CAT, 大型犬: BIT_DOG, 小型犬: SMALL_DOG, 兔子: RABBIT, 鸟类: BIRD, 其他: OTHER]")
    @NotBlank(message = "动物种类不能为空")
    private String animalType;
    
    @ApiModelProperty(value = "动物性别[雄性:MALE, 雌性:FEMALE]")
    @NotBlank(message = "动物性别不能为空")
    private String animalGender;
    
    @ApiModelProperty(value = "受伤程度[饥饿: HUNGER, 外伤(较轻): TRAUMA_LIGHTER, 外伤(较重): TRAUMA_SERIOUS, 怀孕: PREGNANT, 绝育: STERILIZATION, 狂犬病: RABIES, 气管炎: TRACHEITIS, 口腔炎: STOMATITIS, 皮肤病: SKIN_DISEASE, 犬瘟: CANINE_DISTEMPER, 其他: OTHER]")
    @NotBlank(message = "受伤程度不能为空")
    private String injuryDegree;
    
    @ApiModelProperty(value = "地点")
    @NotBlank(message = "地点不能为空")
    private String address;
    
    @ApiModelProperty(value = "具体地点")
    @NotBlank(message = "具体地点不能为空")
    private String addressDetail;
    
    @ApiModelProperty(value = "申请原因")
    @NotBlank(message = "申请原因不能为空")
    private String applyReason;
    
    @ApiModelProperty(value = "申请照片")
    private String applyImg;
}
