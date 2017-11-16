package com.lanou.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dllo on 17/11/14.
 */
@Controller
public class baseController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping({"/login","/"})
    public String login(){
        return "login";
    }


}
