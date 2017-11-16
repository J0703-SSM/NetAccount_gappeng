package com.lanou.cost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dllo on 17/11/13.
 */
@Controller
@RequestMapping("/fee")
public class MainController {

    @RequestMapping("/role_list")
    public String role_list(){
        return "role/role_list";
    }

    @RequestMapping("/admin_list")
    public String admin_list(){
        return "admin/admin_list";
    }

    @RequestMapping("/account_list")
    public String account_list(){
        return "account/account_list";
    }

    @RequestMapping("/service_list")
    public String service_list(){
        return "service/service_list";
    }

    @RequestMapping("/bill_list")
    public String bill_list(){
        return "bill_list/bill_list";
    }

    @RequestMapping("report_list")
    public String report_list(){
        return "report/report_list";
    }

    @RequestMapping("/user_info")
    public String user_info(){
        return "user_info/user_info";
    }


    @RequestMapping("/user_modi_pwd")
    public String user_modi_pwd(){
        return "user/user_modi_pwd";
    }

}
