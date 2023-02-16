package com.wjq.af.service.user;

import com.wjq.af.dto.response.user.RoleDtoResult;
import com.wjq.af.pojo.user.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-15
 */
public interface RoleService extends IService<Role> {
    
    /**
     * 获取用户角色
     *
     * @param userId 用户 ID
     * @return {@link RoleDtoResult}
     */
    List<RoleDtoResult> getUserRoleList (Long userId);

}
