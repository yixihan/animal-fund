package com.wjq.af.controller.report;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 审核 OpenApi
 * </p>
 *
 * @author yixihan
 * @date 2023/2/20 10:44
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/open/report")
@Api(tags = "审核 OpenApi")
public class ReportController {
}
