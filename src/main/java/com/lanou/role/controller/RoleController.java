package com.lanou.role.controller;

import com.lanou.module.domain.Module;
import com.lanou.module.service.ModuleService;
import com.lanou.role.domain.Role;
import com.lanou.role.domain.RoleModule;
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

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/11/16.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private ModuleService moduleService;
    private int pageSize=3;



    /**
     * 分页查询
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/role_list")
    public String role_list(Integer pageNum,Model model){
        if (pageNum==null){
            pageNum=1;
        }
        PageBean<Role> pageBean = roleService.findAllRole(pageNum, pageSize);
        model.addAttribute("pageBean",pageBean);
        return "role/role_list";
    }

    /**
     * 跳到增加
     * @return
     */
    @RequestMapping("/role_add")
    public String role_add(){
        return "role/role_add";
    }

    /**
     * 保存增加
     * @param
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/role_save")
    public AjaxResult<Role> role_save(String Ids,
                                String name){


        String[] modele_Ids = Ids.split(",");

        AjaxResult result = new AjaxResult();
       Integer[] moduleIds = new Integer[modele_Ids.length];
        for (int i = 0; i < modele_Ids.length; i++) {
            moduleIds[i] = Integer.parseInt(modele_Ids[i]);
        }
        int count = roleService.saveRole(name, moduleIds);
        result.setCount(count);
        return result;
    }

    /**
     * 删除
     * @param role_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/role_delete")
    public AjaxResult<Role> role_delete(int role_id){
        AjaxResult<Role> result = new AjaxResult<Role>();
        int count = roleService.deleteRoleById(role_id);
        result.setCount(count);
        System.out.println(count);
        return result;
    }

    /**
     * 修改前表单回显
     * @param role_id
     * @param model
     * @return
     */
    @RequestMapping("/role_modi")
    public String role_modi(int role_id, Model model){
        Role role = roleService.findByRoleId(role_id);
        List<Module> moduleList = moduleService.findAllModule();
        System.out.println(role);
        System.out.println(role.getModules());
        System.out.println(moduleList);
        model.addAttribute("role",role);
        model.addAttribute("moduleList",moduleList);
        return "role/role_modi";
    }

    /**
     * 保存修改
     * @param moduleIds
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/role_modisave")
    public AjaxResult<Role> modisave(@RequestParam(value = "moduleIds[]") Integer[] moduleIds,
                                     Role role){
        AjaxResult<Role> result = new AjaxResult<Role>();

        List<RoleModule> roleModules = roleService.updateRoleModule(role, moduleIds);

        if (roleModules!=null && roleModules.size()>0){
         result.setCount(1);
        }else {
            result.setCount(0);
        }

        return result;
    }



}
