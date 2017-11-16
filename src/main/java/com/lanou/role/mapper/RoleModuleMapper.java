package com.lanou.role.mapper;

import com.lanou.role.domain.RoleModule;

import java.util.List;

/**
 * Created by dllo on 17/11/16.
 */
public interface RoleModuleMapper {

    int saveRoleModuleBatch(List<RoleModule> roleModuleList);

    int deleteModuleByRoleId(int role_id);
}
