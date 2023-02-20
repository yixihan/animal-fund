package com.wjq.af.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjq.af.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2023-02-15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
