package com.wjq.af.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjq.af.dto.request.user.ModifyUserDtoReq;
import com.wjq.af.dto.request.user.RegisterUserDtoReq;
import com.wjq.af.dto.request.user.RegisterVolunteerDtoReq;
import com.wjq.af.dto.response.user.UserDtoResult;
import com.wjq.af.pojo.user.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-15
 */
public interface UserService extends IService<User> {
    
    /**
     * 获取当前登录用户缓存用户信息
     *
     * @return {@link UserDtoResult}
     */
    UserDtoResult getCacheUserInfo();
    
    /**
     * 获取指定用户用户信息
     *
     * @param userId 用户 ID
     * @return {@link UserDtoResult}
     */
    UserDtoResult getUserInfo(Long userId);
    
    /**
     * 注册用户
     *
     * @param req 请求参数
     */
    void registerUser(RegisterUserDtoReq req);
    
    /**
     * 注册志愿者
     *
     * @param req 请求参数
     */
    void registerVolunteer(RegisterVolunteerDtoReq req);
    
    /**
     * 用户信息修改
     *
     * @param req 请求参数
     */
    void modifyUserInfo(ModifyUserDtoReq req);
    
    /**
     * 用户登出
     *
     */
    void logout();
    
    /**
     * 用户注销
     */
    void cancellation();
}
