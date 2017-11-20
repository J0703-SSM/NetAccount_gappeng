package com.lanou.base.controller;
import com.lanou.admin.domain.Admin;
import com.lanou.admin.service.AdminService;
import com.lanou.module.domain.Module;
import com.lanou.role.domain.Role;
import com.lanou.util.AjaxResult;
import com.lanou.util.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        String verifyCode1 = admin.getVerifyCode();

        Admin admin1 = adminService.findBydmin(admin);

        if (verifyCode.equalsIgnoreCase(verifyCode1) && admin1 != null) {
//            List<Module> list = new ArrayList<Module>();
//            for (Role role : admin1.getRoles()) {
//                List<Module> modules = role.getModules();
//                list.addAll(modules);
//            }
//            for (int i = 0; i < list.size(); i++) {
//                for (int j = list.size()-1; j > i; j--) {
//                    if (list.get(j).getModule_id() == list.get(i).getModule_id()){
//                        list.remove(j);
//                    }
//                }
//            }
//            List<Role> roles = admin1.getRoles();
//            if (roles.size()>0){
//                for (int i = 0; i <roles.size(); i++) {
//                    roles.get(i).setModules(new ArrayList<Module>());
//                }
//                roles.get(0).setModules(list);
//            }
            request.getServletContext().setAttribute("admin",admin1);
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
