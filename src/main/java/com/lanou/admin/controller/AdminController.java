package com.lanou.admin.controller;

import com.lanou.admin.domain.Admin;
import com.lanou.admin.domain.AdminRole;
import com.lanou.admin.service.AdminService;
import com.lanou.role.domain.Role;
import com.lanou.role.service.RoleService;
import com.lanou.util.AjaxResult;
import com.lanou.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dllo on 17/11/16.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private RoleService roleService;



    private int pageSize=3;

    @RequestMapping("/admin_list")
    public String admin_list(Integer pageNum, Model model){
        if (pageNum==null){
            pageNum=1;
        }
        PageBean<Admin> pageBean = adminService.findAllAdmin(pageNum, pageSize);
        model.addAttribute("pageBean",pageBean);
        return "admin/admin_list";
    }

    @RequestMapping("/admin_add")
    public String admin_add(Model model){
        List<Role> roles = roleService.findAllRoleNoPage();
        model.addAttribute("roles",roles);
        return "admin/admin_add";
    }

    @ResponseBody
    @RequestMapping("/admin_addsave")
    public AjaxResult<Admin> admin_addsave(Admin admin,@RequestParam(value = "role_ids[]") Integer[] role_ids){
        AjaxResult<Admin> result = new AjaxResult<Admin>();
        admin.setEnrolldate(new Timestamp(System.currentTimeMillis()));
        int count = adminService.saveAdmin(admin, role_ids);
        result.setCount(count);
        return result;
    }

    @ResponseBody
    @RequestMapping("/admin_delete")
    public AjaxResult<Admin> admin_delete(int admin_id){
        AjaxResult<Admin> result = new AjaxResult<Admin>();
        int count = adminService.deleteAdminById(admin_id);
        result.setCount(count);
        return result;
    }

    @RequestMapping("/admin_modi")
    public String admin_modi( int admin_id,Model model){
        Admin admin = adminService.findAdminById(admin_id);
        List<Role> roleList = roleService.findAllRoleNoPage();
        model.addAttribute("admin",admin);
        model.addAttribute("roleList",roleList);
        return "admin/admin_modi";
    }

    @ResponseBody
    @RequestMapping("/admin_addmodisave")
    public AjaxResult<Admin> admin_addmodisave(Admin admin,@RequestParam(value = "role_ids[]") Integer[] role_ids){
        AjaxResult<Admin> result = new AjaxResult<Admin>();
        int count = adminService.updateAdminRole(admin, role_ids);
        result.setCount(count);
        return result;
    }
    @ResponseBody
    @RequestMapping("/admin_resetPwd")
    public AjaxResult<Admin> admin_resetPwd(@RequestParam(value = "pwd_ids[]") Integer[] pwd_ids){
        AjaxResult<Admin> result = new AjaxResult<Admin>();
        Admin admin = new Admin();
        admin.setPassword("111");
        for (Integer admin_id : pwd_ids) {
            admin.setAdmin_id(admin_id);
            int count = adminService.resetPwd(admin);
            result.setCount(count);
        }
        return result;

    }
}
