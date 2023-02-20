package com.wjq.af.pojo.comment;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 留言板举报表
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CommentReport对象", description = "留言板举报表")
public class CommentReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "留言板主键 ID")
    private Long messageId;
    
    @ApiModelProperty(value = "举报留言类型[ROOT,REPLY]")
    private String messageType;
    
    @ApiModelProperty(value = "举报用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "举报原因")
    private String reportReason;
    
    @ApiModelProperty(value = "举报状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]")
    private String reportStatus;
    
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    
    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;
    
    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer delFlag;
    
    
    public static final String ID = "id";
    
    public static final String MESSAGE_ID = "message_id";
    
    public static final String MESSAGE_TYPE = "message_type";
    
    public static final String USER_ID = "user_id";
    
    public static final String REPORT_REASON = "report_reason";
    
    public static final String REPORT_STATUS = "report_status";
    
    public static final String CREATE_TIME = "create_time";
    
    public static final String UPDATE_TIME = "update_time";
    
    public static final String VERSION = "version";
    
    public static final String DEL_FLAG = "del_flag";
    
}
