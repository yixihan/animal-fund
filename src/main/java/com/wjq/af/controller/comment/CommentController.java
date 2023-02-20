package com.wjq.af.controller.comment;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 留言板 OpenApi
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/open/comment")
@Api(tags = "留言板 OpenApi")
public class CommentController {

}
