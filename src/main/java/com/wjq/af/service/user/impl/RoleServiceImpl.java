package com.wjq.af.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.af.dto.response.user.RoleDtoResult;
import com.wjq.af.mapper.user.RoleMapper;
import com.wjq.af.pojo.user.Role;
import com.wjq.af.pojo.user.UserRole;
import com.wjq.af.service.user.RoleService;
import com.wjq.af.service.user.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    
    @Resource
    private UserRoleService userRoleService;
    
    @Override
    public List<RoleDtoResult> getUserRoleList(Long userId) {
        // 获取用户-角色对应关系
        List<Long> roleIdList = userRoleService.lambdaQuery ()
                .eq (UserRole::getUserId, userId)
                .list ()
                .stream ()
                .map (UserRole::getRoleId)
                .collect (Collectors.toList ());
        
        if (CollectionUtil.isEmpty (roleIdList)) {
            return new ArrayList<> ();
        }
        
        List<Role> roleList = this.lambdaQuery ()
                .in (Role::getId, roleIdList)
                .list ();
        
        return BeanUtil.copyToList (roleList, RoleDtoResult.class);
    }
}
