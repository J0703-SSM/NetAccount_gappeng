package com.lanou.admin.mapper;

import com.lanou.admin.domain.Admin;
import com.lanou.util.PageBean;

import java.util.List;

/**
 * Created by dllo on 17/11/16.
 */
public interface AdminMapper {

    int findCount();

    List <Admin> findAllAdmin(PageBean<Admin> pageBean);

    int findMaxId();

    int saveAdmin(Admin admin);

    int deleteAdminById(int admin_id);

    Admin findAdminById(int admin_id);

    int updateAdmin(Admin admin);

    Admin findAdminByCode(String admin_code);

    Admin findByAdmin(Admin admin);

    Admin findAdminByCodeAndPasw(String admin_code, String password);
}
