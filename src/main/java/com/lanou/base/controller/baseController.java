package com.lanou.base.controller;

import com.lanou.admin.domain.Admin;
import com.lanou.admin.service.AdminService;
import com.lanou.util.AjaxResult;
import com.lanou.util.VerifyCode;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by dllo on 17/11/14.
 */
@Controller
public class baseController {

    @Resource
    private AdminService adminService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping({"/login", "/"})
    public String login() {
        return "login";
    }

    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage image = verifyCode.getImage();


        request.getSession().setAttribute("verifyCode", verifyCode.getText());

        VerifyCode.output(image, response.getOutputStream());

    }

    @RequestMapping("/checkLogin")
    public String checkLogin(Admin admin,
                             HttpServletRequest request,
                             Model model) {
        String verifyCode = (String) request.getSession().getAttribute("verifyCode");
        System.out.println(verifyCode);
        String verifyCode1 = admin.getVerifyCode();
        System.out.println(verifyCode1);
        Admin admin1 = adminService.findBydmin(admin);

        if (verifyCode.equalsIgnoreCase(verifyCode1) && admin1 != null) {
            return "index";
        }
        if (admin1 == null) {
            model.addAttribute("adminErr", "用户名或密码错误");
            return "login";
        }
        if (!verifyCode.equalsIgnoreCase(verifyCode1)) {
            model.addAttribute("codeErr", "验证码错误");
            return "login";
        }


        return "index";

    }


    @ResponseBody
    @RequestMapping("/checkAdmin")
    public AjaxResult checkAdmin(String admin_code) {
        AjaxResult<Admin> result = new AjaxResult<Admin>();
        Admin admin = adminService.findAdminByCode(admin_code);
        if (admin == null) {
            result.setMessage("该用户不存在");
        }
        return result;
    }


}
