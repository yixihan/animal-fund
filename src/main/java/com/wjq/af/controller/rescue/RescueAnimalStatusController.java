package com.wjq.af.controller.rescue;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 救援动物状态 OpenApi
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/open/rescue/status")
@Api(tags = "救援动物状态 OpenApi")
public class RescueAnimalStatusController {

}
