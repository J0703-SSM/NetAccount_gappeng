package com.lanou.base.interceptor;

import com.lanou.admin.domain.Admin;
import com.lanou.module.domain.Module;
import com.lanou.role.domain.Role;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dllo on 17/11/18.
 */
public class AdminInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Admin admin = (Admin) request.getServletContext().getAttribute("admin");
        if (admin==null){
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
        }

        String uri = request.getRequestURI();
        if (uri.contains("index")){
            return true;
        }
        if (uri.contains("user")){
            return true;
        }
        int module_id = 0;
        if (uri.contains("role")){
            module_id =1;
        }
        if (uri.contains("admin")){
            module_id=2;
        }
        if (uri.contains("fee")){
            module_id=3;
        }
        if (uri.contains("account")){
            module_id=4;
        }
        if (uri.contains("service")){
            module_id=5;
        }
        if (uri.contains("bill")){
            module_id=6;
        }
        if (uri.contains("report")){
            module_id=7;
        }

        for (Role role : admin.getRoles()) {
            for (Module module : role.getModules()) {
                if (module.getModule_id() == module_id){
                    return true;
                }
            }
        }
        request.getRequestDispatcher("/WEB-INF/pages/nopower.jsp").forward(request,response);
        return true;
    }



    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
