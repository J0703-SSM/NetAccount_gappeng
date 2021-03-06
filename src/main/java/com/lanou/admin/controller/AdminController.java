package com.lanou.admin.controller;

import com.lanou.admin.domain.Admin;
import com.lanou.admin.domain.AdminRole;
import com.lanou.admin.service.AdminService;
import com.lanou.module.domain.Module;
import com.lanou.module.service.ModuleService;
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
    @Resource
    private ModuleService moduleService;
    private int pageSize=3;

    /**
     * 分页查询所有
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/admin_list")
    public String admin_list(Integer pageNum, Model model){
        if (pageNum==null){
            pageNum=1;
        }
        PageBean<Admin> pageBean = adminService.findAllAdmin(pageNum, pageSize);
        model.addAttribute("pageBean",pageBean);
        List<Module> modules = moduleService.findAllModule();
        model.addAttribute("modules",modules);
        return "admin/admin_list";
    }

    /**
     * 调到增加页面
     * @param model
     * @return
     */
    @RequestMapping("/admin_add")
    public String admin_add(Model model){
        List<Role> roles = roleService.findAllRoleNoPage();
        model.addAttribute("roles",roles);
        return "admin/admin_add";
    }

    /**
     * 保存
     * @param admin
     * @param role_ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin_addsave")
    public AjaxResult<Admin> admin_addsave(Admin admin,@RequestParam(value = "role_ids[]") Integer[] role_ids){
        AjaxResult<Admin> result = new AjaxResult<Admin>();
        admin.setEnrolldate(new Timestamp(System.currentTimeMillis()));
        int count = adminService.saveAdmin(admin, role_ids);
        result.setCount(count);
        return result;
    }

    /**
     * 删除
     * @param admin_id
     * @return
     */

    @ResponseBody
    @RequestMapping("/admin_delete")
    public AjaxResult<Admin> admin_delete(int admin_id){
        AjaxResult<Admin> result = new AjaxResult<Admin>();
        int count = adminService.deleteAdminById(admin_id);
        result.setCount(count);
        return result;
    }

    /**
     * 修改表单回显
     * @param admin_id
     * @param model
     * @return
     */
    @RequestMapping("/admin_modi")
    public String admin_modi( int admin_id,Model model){
        Admin admin = adminService.findAdminById(admin_id);
        List<Role> roleList = roleService.findAllRoleNoPage();
        model.addAttribute("admin",admin);
        model.addAttribute("roleList",roleList);
        return "admin/admin_modi";
    }

    /**
     * 保存修改
     * @param admin
     * @param role_ids
     * @return
     */

    @ResponseBody
    @RequestMapping("/admin_addmodisave")
    public AjaxResult<Admin> admin_addmodisave(Admin admin,@RequestParam(value = "role_ids[]") Integer[] role_ids){
        AjaxResult<Admin> result = new AjaxResult<Admin>();
        int count = adminService.updateAdminRole(admin, role_ids);
        result.setCount(count);
        return result;
    }

    /**
     * 重置密码
     * @param pwd_ids
     * @return
     */
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

    /**
     * 高级查询
     * @param pageNum
     * @param module_id
     * @param role_name
     * @param model
     * @return
     */
    @RequestMapping("/admin_query")
    public String admin_query(Integer pageNum,Integer module_id,String role_name,Model model){
        if (module_id ==null){
            module_id=-1;
        }
        if (role_name==null){
            role_name="";
        }
        model.addAttribute("module_id",module_id);
        model.addAttribute("role_name",role_name);
        if (pageNum==null){
            pageNum=1;
        }
        if(module_id==-1 && role_name.trim().length()==0){
            admin_list(pageNum,model);
        }else {
            PageBean<Admin> pageBean = adminService.findAdminByQuery(pageNum, pageSize, module_id, role_name);
            model.addAttribute("pageBean", pageBean);
        }
        List<Module> modules = moduleService.findAllModule();
        System.out.println(modules);
        model.addAttribute("modules",modules);
        return "admin/admin_list";
    }
}
