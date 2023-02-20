package com.wjq.af.dto.request.rescue;

import com.wjq.af.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 搜索救援动物信息-dtoReq
 *
 * @author yixihan
 * @date 2023/2/20 16:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("搜索救援动物信息-dtoReq")
public class QueryRescueAnimalInfoDtoReq extends PageDtoReq {
    
    @ApiModelProperty(value = "动物种类[猫: CAT, 大型犬: BIT_DOG, 小型犬: SMALL_DOG, 兔子: RABBIT, 鸟类: BIRD, 其他: OTHER]")
    private String animalType;
    
    @ApiModelProperty(value = "受伤程度[饥饿: HUNGER, 外伤(较轻): TRAUMA_LIGHTER, 外伤(较重): TRAUMA_SERIOUS, 怀孕: PREGNANT, 绝育: STERILIZATION, 狂犬病: RABIES, 气管炎: TRACHEITIS, 口腔炎: STOMATITIS, 皮肤病: SKIN_DISEASE, 犬瘟: CANINE_DISTEMPER, 其他: OTHER]")
    private String injuryDegree;
    
}
