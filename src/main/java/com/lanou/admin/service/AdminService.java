package com.lanou.admin.service;

import com.lanou.admin.domain.Admin;
import com.lanou.admin.domain.AdminRole;
import com.lanou.util.PageBean;

import java.util.List;

/**
 * Created by dllo on 17/11/16.
 */
public interface AdminService {
    PageBean<Admin> findAllAdmin(Integer pageNum, int pageSize);

    int saveAdmin(Admin admin, Integer[] role_ids);

    int deleteAdminById(int admin_id);

    Admin findAdminById(int admin_id);

    int updateAdminRole(Admin admin, Integer[] role_ids);

    Admin findAdminByCode(String admin_code);

    Admin findBydmin(Admin admin);

    Admin findAdminByCodeAndPasw(String admin_code, String password);

    int resetPwd(Admin admin);

    PageBean<Admin> findAdminByQuery(Integer pageNum, int pageSize, Integer module_id, String role_name);
}
