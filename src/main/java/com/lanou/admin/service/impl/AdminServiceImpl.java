package com.lanou.admin.service.impl;

import com.lanou.admin.domain.Admin;
import com.lanou.admin.domain.AdminRole;
import com.lanou.admin.mapper.AdminMapper;
import com.lanou.admin.mapper.AdminRoleMapper;
import com.lanou.admin.service.AdminService;
import com.lanou.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/11/16.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private  AdminMapper adminMapper;

   @Resource
    private AdminRoleMapper adminRoleMapper;

    public PageBean<Admin> findAllAdmin(Integer pageNum, int pageSize) {
        int totalCount = adminMapper.findCount();
        PageBean<Admin> pageBean = new PageBean<Admin>(pageNum,pageSize,totalCount);
        List<Admin> admins = adminMapper.findAllAdmin(pageBean);
        pageBean.setData(admins);
        return pageBean;
    }

    public int saveAdmin(Admin admin, Integer[] role_ids) {
       //管路员id
        int admin_id = adminMapper.findMaxId() + 1000;

        admin.setAdmin_id(admin_id);
        int counAdmin = adminMapper.saveAdmin(admin);
        System.out.println(counAdmin);


        if (role_ids != null && role_ids.length>0){
            List<AdminRole> adminRoleList= new ArrayList<AdminRole>();
            for (int i = 0; i < role_ids.length; i++) {
               AdminRole adminRole = new AdminRole(admin_id,role_ids[i]);
               adminRoleList.add(adminRole);
            }
            int i = adminRoleMapper.saveAdminRoleBatch(adminRoleList);
            System.out.println(i);
        }
        return counAdmin;
    }

    public int deleteAdminById(int admin_id) {
        adminRoleMapper.deleteAdminRoleById(admin_id);
        return adminMapper.deleteAdminById(admin_id);
    }

    public Admin findAdminById(int admin_id) {
       return adminMapper.findAdminById(admin_id);

    }

    public int updateAdminRole(Admin admin, Integer[] role_ids) {
        int admin_id = admin.getAdmin_id();
        //根据admin_id先删除admin_role表中的数据
        int delCount = adminRoleMapper.deleteAdminRoleByRoleId(admin_id);

        int countAdmin = adminMapper.updateAdmin(admin);


        List<AdminRole> adminRoles = new ArrayList<AdminRole>();
        for (Integer role_id :role_ids) {
           AdminRole adminRole = new AdminRole(admin_id,role_id);

            adminRoles.add(adminRole);
        }

        if (role_ids != null && role_ids.length>0){
            List<AdminRole> adminRoleList= new ArrayList<AdminRole>();
            for (int i = 0; i < role_ids.length; i++) {
                AdminRole adminRole = new AdminRole(admin_id,role_ids[i]);
                adminRoleList.add(adminRole);
            }
            adminRoleMapper.saveAdminRoleBatch(adminRoleList);

        }
        return countAdmin;

    }

    public Admin findAdminByCode(String admin_code) {
       return adminMapper.findAdminByCode(admin_code);

    }

    public Admin findBydmin(Admin admin) {
        return adminMapper.findByAdmin(admin);
    }

    public Admin findAdminByCodeAndPasw(String admin_code, String password) {
       return adminMapper.findAdminByCodeAndPasw(admin_code,password);

    }
}
