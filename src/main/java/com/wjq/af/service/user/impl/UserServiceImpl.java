package com.wjq.af.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.af.auth.enums.RoleEnums;
import com.wjq.af.auth.service.TokenService;
import com.wjq.af.dto.request.thirdpart.RealNameAuthDtoReq;
import com.wjq.af.dto.request.user.ModifyUserDtoReq;
import com.wjq.af.dto.request.user.RegisterUserDtoReq;
import com.wjq.af.dto.request.user.RegisterVolunteerDtoReq;
import com.wjq.af.dto.response.user.UserDtoResult;
import com.wjq.af.enums.ExamineStatusEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.mapper.user.UserMapper;
import com.wjq.af.pojo.user.User;
import com.wjq.af.pojo.user.UserRole;
import com.wjq.af.service.thirdpart.RealNameAuthService;
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
    
    @Resource
    private RealNameAuthService realNameAuthService;
    
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
    public void registerUser(RegisterUserDtoReq req) {
        // 实名认证
        realNameAuth (req);
    
        User user = BeanUtil.toBean (req, User.class);
        // 保存用户表
        saveUser (user);
        // 保存角色表
        saveUserRole (user.getId (), RoleEnums.USER);
    }
    
    @Override
    @Transactional(rollbackFor = BizException.class)
    public void registerVolunteer(RegisterVolunteerDtoReq req) {
        // 实名认证
        realNameAuth (req);
        
        User user = BeanUtil.toBean (req, User.class);
        user.setExamineStatus (ExamineStatusEnums.UN_EXAMINE.getValue ());
        
        // 保存用户表
        saveUser (user);
        // 保存角色表
        saveUserRole (user.getId (), RoleEnums.VOLUNTEER);
    }
    
    @Override
    public void modifyUserInfo(ModifyUserDtoReq req) {
        // 获取登录用户用户 ID
        Long userId = tokenService.getCacheUserId ();
        // 获取乐观锁
        Integer version = this.getById (userId).getVersion ();
        User user = BeanUtil.toBean (req, User.class);
        user.setId (userId);
        user.setVersion (version);
    
        Assert.isTrue (this.updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void logout() {
        tokenService.logout ();
    }
    
    @Override
    public void cancellation() {
        // 删除账户
        this.removeById (tokenService.getCacheUserId ());
        // 登出
        logout ();
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
    
    /**
     * 实名认证
     *
     * @param req 注册请求参数
     */
    private void realNameAuth(RegisterUserDtoReq req) {
        RealNameAuthDtoReq authReq = new RealNameAuthDtoReq ();
        authReq.setIdCard (req.getUserIdCard ());
        authReq.setRealName (req.getUserFullName ());
        realNameAuthService.auth (authReq);
    }
}
