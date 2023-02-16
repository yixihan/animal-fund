package com.wjq.af.mapper.user;

import com.wjq.af.pojo.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
