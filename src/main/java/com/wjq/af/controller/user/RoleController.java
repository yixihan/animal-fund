package com.wjq.af.controller.user;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色 OpenApi
 * </p>
 *
 * @author yixihan
 * @since 2023-02-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/open/role")
@Api(tags = "角色 OpenApi")
public class RoleController {

}
