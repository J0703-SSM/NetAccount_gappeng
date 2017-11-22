package com.lanou.account.controller;

import com.lanou.account.domain.Account;
import com.lanou.account.service.AccountService;
import com.lanou.util.AjaxResult;
import com.lanou.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 17/11/15.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;
    private int pageSize =3;

    /**
     * 分页查询所有
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/account_list")
    public String account(Integer pageNum, Model model){
        if (pageNum==null){
            pageNum=1;
        }
        PageBean<Account> pageBean = accountService.findAll(pageNum, pageSize);
        model.addAttribute("pageBean",pageBean);
        return "account/account_list";
    }

    /**
     * 跳到增加界面
     * @return
     */
    @RequestMapping("/account_add")
    public String account_add(){
        return "/account/account_add";
    }

    /**
     * 保存增加
     * @param account
     * @return
     */
    @ResponseBody
    @RequestMapping("/accountAdd")
    public AjaxResult<Account> accountAdd(Account account){
        AjaxResult<Account> result = new AjaxResult<Account>();
        String idcard_no = account.getIdcard_no();
        String id = account.getRecommender_idcard();
        System.out.println(id);
        //根据具推荐人的身份证id去查找account中是否存在此人
        Account accountByIdCard = accountService.findByIdCard(id);
        if (accountByIdCard!=null){
            Integer recommender_id = accountByIdCard.getAccount_id();
            account.setRecommender_id(recommender_id);
            String birthday = getBirthday(idcard_no);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
               Date date = sdf.parse(birthday);
                System.out.println(date);
                account.setBirthdate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            account.setStatus("1");
            account.setCreate_date(new Timestamp(System.currentTimeMillis()));
            int count = accountService.save(account);
            result.setCount(count);
        }else {
            result.setCount(0);

        }
        return result;
    }


    /**
     * 删除 将状态改为3
     * @param account
     * @return
     */
    @ResponseBody
    @RequestMapping("/account_delete")
    public AjaxResult<Account> account_delete(Account account){
        AjaxResult<Account> result = new AjaxResult<Account>();
        account.setClose_date(new Timestamp(System.currentTimeMillis()));
        account.setStatus("3");
        int count = accountService.deleteAccount(account);
        result.setCount(count);
        return result;
    }

    /**
     * 编辑表单回显
     * @param model
     * @param account_id
     * @return
     */
    @RequestMapping("/account_modi")
    public String account_modi(Model model,Integer account_id){
        System.out.println(account_id);
        Account account = accountService.findById(account_id);
        //通过推荐人id找到推荐人   得到推荐人的身份证号
        Integer recommender_id = account.getRecommender_id();
        Account accountByRecommenderId = accountService.findByRecommenderId(recommender_id);
        if (accountByRecommenderId!=null){
            String idcard_no = accountByRecommenderId.getIdcard_no();
            //将推荐人的身份证号保存到account中
            account.setRecommender_idcard(idcard_no);

        }else {
            account.setRecommender_idcard(null);
        }
        model.addAttribute("account",account);
        return "account/account_modi";
    }

    /**
     * 保存修改
     * @param account
     * @return
     */
    @ResponseBody
    @RequestMapping("/account_modisave")
    public AjaxResult<Account> account_modisave(Account account){
        System.out.println(account);
        AjaxResult<Account> result = new AjaxResult<Account>();
        //根据具推荐人的身份证id去查找account中是否存在此人
        String id= account.getRecommender_idcard();
        Account accountByIdCard = accountService.findByIdCard(id);
        System.out.println(accountByIdCard);
        if (accountByIdCard!=null){
            //找到这个人得到account_id 作为recommend_id
            account.setRecommender_id(accountByIdCard.getAccount_id());
            int count = accountService.update(account);
            result.setCount(count);

        }else {
            result.setCount(0);
        }
        return result;
    }

    /**
     * 开通或是暂停
     * @param account
     * @return
     */
    @ResponseBody
    @RequestMapping("/account_beginOrStop")
    public AjaxResult<Account> account_beginOrStop(Account account){
        AjaxResult<Account> result = new AjaxResult<Account>();
        int count =0;
        if (account.getStatus().equals("2")){
            account.setStatus("1");
            count = accountService.setStauts(account);
        }else if (account.getStatus().equals("1")){
            account.setStatus("2");
            account.setPause_date(new Timestamp(System.currentTimeMillis()));
            count= accountService.setStauts(account);
        }
        result.setCount(count);
        return result;
    }

    /**
     * 高级查询
     * @param pageNum
     * @param account
     * @param model
     * @return
     */
    @RequestMapping("/account_query")
    public String account_query(Integer pageNum,Account account,Model model){
        if (account.getStatus()==null){
            account.setStatus("-1");
        }
        model.addAttribute("idcard_no",account.getIdcard_no());
        model.addAttribute("real_name",account.getReal_name());
        model.addAttribute("login_name",account.getLogin_name());
        model.addAttribute("status",account.getStatus());

        if (pageNum==null){
            pageNum=1;
        }
        PageBean<Account> pageBean = accountService.findAllAccount(pageNum, pageSize, account);
        model.addAttribute("pageBean",pageBean);
        return "account/account_list";
    }

    /**
     * 查看详细信息
     * @return
     */
    @RequestMapping("/account_detail/{account_id}")
    public String account_detail(@PathVariable int account_id,Model model){
        Account account = accountService.findById(account_id);
        model.addAttribute("account",account);
        return "account/account_detail";
    }



    public String getBirthday(String id){
        if (id.length()==18){
            StringBuffer sb = new StringBuffer();
            sb.append(id.substring(6,10)).append("-").append(id.substring(10,12)).append("-").append(id.substring(12,14));
            return sb.toString();
        }else {
            return null;
        }
    }


}
