package com.wjq.af.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.af.auth.enums.RoleEnums;
import com.wjq.af.auth.service.TokenService;
import com.wjq.af.dto.request.auth.ResetPasswordDtoReq;
import com.wjq.af.dto.request.user.RegisterUserDtoReq;
import com.wjq.af.dto.request.user.RegisterVolunteerDtoReq;
import com.wjq.af.dto.response.user.UserDtoResult;
import com.wjq.af.enums.ExamineStatusEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.mapper.user.UserMapper;
import com.wjq.af.pojo.user.User;
import com.wjq.af.pojo.user.UserRole;
import com.wjq.af.service.user.UserRoleService;
import com.wjq.af.service.user.UserService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    
    @Resource
    private TokenService tokenService;
    
    @Resource
    private UserRoleService userRoleService;
    
    @Override
    public UserDtoResult getCacheUserInfo() {
        return tokenService.getCacheUser ();
    }
    
    @Override
    public UserDtoResult getUserInfo(Long userId) {
        User user = this.lambdaQuery ()
                .eq (User::getId, userId)
                .one ();
        Assert.notNull (user, BizCodeEnum.ACCOUNT_NOT_FOUND);
        
        return BeanUtil.toBean (user, UserDtoResult.class);
    }
    
    @Override
    @Transactional(rollbackFor = BizException.class)
    public Boolean registerUser(RegisterUserDtoReq req) {
        User user = BeanUtil.toBean (req, User.class);
        // 保存用户表
        saveUser (user);
        // 保存角色表
        saveUserRole (user.getId (), RoleEnums.USER);
        return Boolean.TRUE;
    }
    
    @Override
    @Transactional(rollbackFor = BizException.class)
    public Boolean registerVolunteer(RegisterVolunteerDtoReq req) {
        User user = BeanUtil.toBean (req, User.class);
        user.setExamineStatus (ExamineStatusEnums.UN_EXAMINE.getValue ());
        // 保存用户表
        saveUser (user);
        // 保存角色表
        saveUserRole (user.getId (), RoleEnums.VOLUNTEER);
        
        return Boolean.TRUE;
    }
    
    @Override
    public Boolean resetPassword(ResetPasswordDtoReq req) {
        // TODO 验证码校验
        
        User user = this.lambdaQuery ()
                .eq (User::getUserEmail, req.getEmail ())
                .one ();
        
        Assert.notNull (user, BizCodeEnum.ACCOUNT_NOT_FOUND);
        
        // 密码加密
        String salt = RandomUtil.randomString (10);
        String password = MD5Util.md5 (req.getPassword (), salt);
        user.setUserPassword (password);
        user.setUserSalt (salt);
        
        Assert.isTrue (this.updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        return Boolean.TRUE;
    }
    
    /**
     * 新增用户角色
     *
     * @param userId    用户 ID
     * @param roleEnums 角色 {@link RoleEnums}
     */
    private void saveUserRole(Long userId, RoleEnums roleEnums) {
        UserRole userRole = new UserRole ();
        userRole.setUserId (userId);
        userRole.setRoleId (roleEnums.getRoleId ());
        
        // 保存数据库
        Assert.isTrue (userRoleService.save (userRole), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    /**
     * 新增用户
     *
     * @param user 用户
     */
    private void saveUser(User user) {
        // 密码加密
        String salt = RandomUtil.randomString (10);
        String password = MD5Util.md5 (user.getUserPassword (), salt);
        user.setUserPassword (password);
        user.setUserSalt (salt);
        
        // 保存数据库
        Assert.isTrue (this.save (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
}
