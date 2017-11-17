package com.lanou.admin.mapper;

import com.lanou.admin.domain.AdminRole;

import java.util.List;

/**
 * Created by dllo on 17/11/17.
 */
public interface AdminRoleMapper {
    int saveAdminRoleBatch(List<AdminRole> adminRoleList);

    int deleteAdminRoleById(int admin_id);

    int deleteAdminRoleByRoleId(int role_id);


}
