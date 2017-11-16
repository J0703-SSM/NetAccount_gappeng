package com.lanou.role.service.impl;

import com.lanou.role.domain.Role;
import com.lanou.role.domain.RoleModule;
import com.lanou.role.mapper.RoleMapper;
import com.lanou.role.mapper.RoleModuleMapper;
import com.lanou.role.service.RoleService;
import com.lanou.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/11/16.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleModuleMapper roleModuleMapper;


    public PageBean<Role> findAllRole(Integer pageNum, int pageSize) {
        int totalCount = roleMapper.findCount();
        PageBean<Role> pageBean = new PageBean(pageNum,pageSize,totalCount);
        List<Role> roles = roleMapper.findAllRole(pageBean);
        pageBean.setData(roles);
        return pageBean;
    }

    public int saveRole(String name, Integer[] moduleIds) {
         /*先查找新建的角色名是否已经存在在数据库表中*/
        Role role = roleMapper.selectRoleByName(name);
        if (role!=null){
            return 0;
        }
        /*角色id 在原来最大值的基础上加100*/
         int role_id =  roleMapper.findMaxRoleId() + 100;
         Role newrole= new Role(role_id,name);
        int roleCount = roleMapper.saveRole(newrole);

        System.out.println(roleCount);

        if (moduleIds != null && moduleIds.length>0){
            List<RoleModule> roleModuleList = new ArrayList<RoleModule>();
            for (int i = 0; i < moduleIds.length; i++) {
                RoleModule roleModule = new RoleModule(role_id,moduleIds[i]);
                roleModuleList.add(roleModule);
            }
            roleModuleMapper.saveRoleModuleBatch(roleModuleList);
        }

        return roleCount;

    }

    public int deleteRoleById(int role_id) {
        roleModuleMapper.deleteModuleByRoleId(role_id);

        return roleMapper.deleteRoleById(role_id);

    }

    public Role findByRoleId(int role_id) {
        return roleMapper.findRoleAndModuleById(role_id);
    }

    public List<RoleModule> updateRoleModule(Integer role_id, Integer[] moduleIds) {
        int delCount = roleModuleMapper.deleteModuleByRoleId(role_id);
        List<RoleModule> roleModules = new ArrayList<RoleModule>();
        for (Integer moduleId : moduleIds) {
            RoleModule roleModule = new RoleModule(role_id,moduleId);

            roleModules.add(roleModule);
        }

        roleModuleMapper.saveRoleModuleBatch(roleModules);
        return roleModules;
    }
}
