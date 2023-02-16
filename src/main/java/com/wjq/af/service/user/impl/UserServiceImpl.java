package com.wjq.af.service.user.impl;

import com.wjq.af.pojo.user.User;
import com.wjq.af.mapper.user.UserMapper;
import com.wjq.af.service.user.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-15
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
