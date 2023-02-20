package com.wjq.af.controller.rescue;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 救援动物资金明细表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/open/rescue/capital")
@Api(tags = "救援动物资金明细 OpenApi")
public class RescueAnimalCapitalDetailController {

}
