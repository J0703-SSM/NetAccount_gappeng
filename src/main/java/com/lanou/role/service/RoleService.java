package com.lanou.role.service;

import com.lanou.admin.domain.Admin;
import com.lanou.admin.domain.AdminRole;
import com.lanou.role.domain.Role;
import com.lanou.role.domain.RoleModule;
import com.lanou.util.PageBean;

import java.util.List;

/**
 * Created by dllo on 17/11/16.
 */
public interface RoleService {

    PageBean<Role> findAllRole(Integer pageNum, int pageSize);

    int saveRole(String name, Integer[] moduleIds);

    int deleteRoleById(int role_id);

    Role findByRoleId(int role_id);

    List<RoleModule> updateRoleModule(Integer role_id, Integer[] moduleIds);

    List<Role> findAllRoleNoPage();


}
