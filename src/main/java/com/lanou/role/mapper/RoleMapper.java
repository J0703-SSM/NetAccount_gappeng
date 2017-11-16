package com.lanou.role.mapper;

import com.lanou.role.domain.Role;
import com.lanou.role.domain.RoleModule;
import com.lanou.util.PageBean;

import java.util.List;

/**
 * Created by dllo on 17/11/16.
 */
public interface RoleMapper {



    int findCount();

    List<Role> findAllRole(PageBean<Role> pageBean);

    Role selectRoleByName(String name);

    Integer findMaxRoleId();

    int saveRole(Role newrole);

    int deleteRoleById(int role_id);


    Role findRoleAndModuleById(int role_id);
}
