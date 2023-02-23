package com.wjq.af.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
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
        User user = lambdaQuery ()
                .eq (User::getId, userId)
                .one ();
        Assert.notNull (user, BizCodeEnum.ACCOUNT_NOT_FOUND);
        
        return BeanUtil.toBean (user, UserDtoResult.class);
    }
    
    @Override
    @Transactional(rollbackFor = BizException.class)
    public void registerUser(RegisterUserDtoReq req) {
        // 校验手机号
        validateMobile (req.getUserMobile ());
        // 校验邮箱
        validateEmail (req.getUserEmail ());
        // 实名认证
        realNameAuth (req);
    
        User user = BeanUtil.toBean (req, User.class);
        // 保存用户表
        saveUser (user);
        // 保存角色表
        saveUserRole (user.getId ());
    }
    
    @Override
    @Transactional(rollbackFor = BizException.class)
    public void registerVolunteer(RegisterVolunteerDtoReq req) {
        // 校验手机号
        validateMobile (req.getUserMobile ());
        // 校验邮箱
        validateEmail (req.getUserEmail ());
        // 实名认证
        realNameAuth (req);
        
        User user = BeanUtil.toBean (req, User.class);
        user.setExamineStatus (ExamineStatusEnums.UN_EXAMINE.getValue ());
        
        // 保存用户表
        saveUser (user);
        // 保存角色表
        saveUserRole (user.getId ());
    }
    
    @Override
    public void modifyUserInfo(ModifyUserDtoReq req) {
        // 校验手机号
        if (StrUtil.isNotBlank (req.getUserMobile ())) {
            validateMobile (req.getUserMobile ());
        }
        // 校验邮箱
        if (StrUtil.isNotBlank (req.getUserEmail ())) {
            validateEmail (req.getUserEmail ());
        }
        // 获取登录用户用户 ID
        Long userId = tokenService.getCacheUserId ();
        // 获取乐观锁
        Integer version = getById (userId).getVersion ();
        User user = BeanUtil.toBean (req, User.class);
        user.setId (userId);
        user.setVersion (version);
    
        Assert.isTrue (updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void logout() {
        tokenService.logout ();
    }
    
    @Override
    public void cancellation() {
        // 删除账户
        removeById (tokenService.getCacheUserId ());
        // 登出
        logout ();
    }
    
    /**
     * 新增用户角色
     *
     * @param userId 用户 ID
     */
    private void saveUserRole(Long userId) {
        UserRole userRole = new UserRole ();
        userRole.setUserId (userId);
        userRole.setRoleId (RoleEnums.USER.getRoleId ());
        
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
        Assert.isTrue (save (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
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
    
    /**
     * 校验手机号
     *
     * @param email 邮箱
     */
    private void validateEmail(String email) {
        Integer count = lambdaQuery ()
                .eq (User::getUserEmail, email)
                .count ();
        
        Assert.isTrue (count == 0, new BizException ("该邮箱已被其他用户绑定"));
    }
    
    /**
     * 校验手机号
     * 
     * @param mobile 手机号
     */
    private void validateMobile(String mobile) {
        Integer count = lambdaQuery ()
                .eq (User::getUserMobile, mobile)
                .count ();
        
        Assert.isTrue (count == 0, new BizException ("该手机号已被其他用户绑定"));
    }
}
