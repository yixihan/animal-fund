package com.wjq.af.service.user;

import com.wjq.af.dto.request.auth.ResetPasswordDtoReq;
import com.wjq.af.dto.request.user.RegisterUserDtoReq;
import com.wjq.af.dto.request.user.RegisterVolunteerDtoReq;
import com.wjq.af.dto.response.user.UserDtoResult;
import com.wjq.af.pojo.user.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 获取当前指定用户用户信息
     *
     * @param userId 用户 ID
     * @return {@link UserDtoResult}
     */
    UserDtoResult getUserInfo(Long userId);
    
    /**
     * 注册用户
     *
     * @param req 请求参数
     * @return true : 注册成功 | false : 注册失败
     */
    Boolean registerUser(RegisterUserDtoReq req);
    
    /**
     * 注册志愿者
     *
     * @param req 请求参数
     * @return true : 注册成功 | false : 注册失败
     */
    Boolean registerVolunteer(RegisterVolunteerDtoReq req);
    
    /**
     * 重置密码
     *
     * @param req 请求参数
     * @return true : 重置成功 | false : 重置失败
     */
    Boolean resetPassword(ResetPasswordDtoReq req);
}
