package com.lanou.cost.controller;

import com.lanou.account.domain.Account;
import com.lanou.util.AjaxResult;
import com.lanou.util.PageBean;
import com.lanou.cost.domain.Cost;
import com.lanou.cost.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/11/13.
 */
@Controller
@RequestMapping("/fee")
public class CostController {
    @Autowired
    @Qualifier("costService")
    private CostService costService;

    private int pageSize =3;




    /**
     * 分页查询所有cost
     * @param model
     * @return
     */
    @RequestMapping("/fee_list")
    public String freeList(Integer pageNum,Model model){
        if (pageNum==null){
            pageNum=1;
        }
        PageBean<Cost> pageBean = costService.findAll(pageNum, pageSize);
        model.addAttribute("pageBean",pageBean);
        return "fee/fee_list";
    }

    /**
     * 跳到增加页面
     * @return
     */
    @RequestMapping("/fee_add")
    public String toFeeAdd(){
        return "fee/fee_add";
    }

    /**
     * 保存增加
     * @param cost
     * @return
     */

    @ResponseBody
    @RequestMapping("/feeAdd")
    public AjaxResult feeAdd(@Validated Cost cost, BindingResult result){
        System.out.println(cost);
        AjaxResult<Cost> ajaxResult = resultMap(cost,result);
        if (ajaxResult.getMaps().size()==0){
            cost.setStatus("0");
            cost.setCreatTime(new Timestamp(System.currentTimeMillis()));
            int count = costService.save(cost);
            ajaxResult.setCount(count);
        }else {
            ajaxResult.setCount(0);
        }
        return ajaxResult;

    }

    /**
     * 修改
     * @param cost
     * @return
     */
    @ResponseBody
    @RequestMapping("/updById")
    public AjaxResult<Cost> updById(@Validated Cost cost,BindingResult result){
        System.out.println(cost);
        AjaxResult<Cost> ajaxResult = resultMap(cost,result);
       if (ajaxResult.getMaps().size()==0){
           int count = costService.updateById(cost);
           System.out.println(count);
           ajaxResult.setCount(count);
       }else {
           ajaxResult.setCount(0);
       }
        return ajaxResult;
    }


    /**
     * 修改之前判断资费是否已经启用
     * @param cost_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/before_fee_modi")
    public AjaxResult<Cost> feemodi(int cost_id){
        AjaxResult<Cost> result = new AjaxResult<Cost>();
        Cost cost = costService.findById(cost_id);
        if (cost.getStatus().equals("0")){
            result.setCount(1);
        }else {
            result.setCount(0);
        }
        return result;
    }

    /**
     * 跳到编辑页面 ,做页面回显
     * @return
     */
    @RequestMapping("/fee_modi")
    public String feemodi(Model model, Integer cost_id){
        Cost cost = costService.findById(cost_id);
        model.addAttribute("cost",cost);
        return "fee/fee_modi";
    }



    /**
     * 删除
     * @param cost_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delById")
    public AjaxResult<Cost> delById(int cost_id){
        System.out.println(cost_id);
        AjaxResult<Cost> result = new AjaxResult<Cost>();
        int count = costService.deleteById(cost_id);
        result.setCount(count);
        return result;
    }

    /**
     * 启用资费
     * @param cost_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/startCost")
    public AjaxResult<Cost> startCost(int cost_id){
        AjaxResult<Cost> result = new AjaxResult<Cost>();
        Cost cost1 = costService.findById(cost_id);
        if (cost1.getStatus().equals("1")){
            result.setCount(0);
        }else {
            Cost cost = new Cost();
            cost.setCost_id(cost_id);
            cost.setStartTime(new Timestamp(System.currentTimeMillis()));
            int count = costService.startCost(cost);
            result.setCount(count);
        }

        return result;
    }

    /**
     * 查看详细信息
     * @return
     */
    @RequestMapping("/fee_detail/{cost_id}")
    public String feedetail(@PathVariable int cost_id,Model model){
        Cost cost = costService.findById(cost_id);
        model.addAttribute("cost",cost);
        return "fee/fee_detail";
    }

    /**
     * 资费排序
     * @param pageNum
     * @param sort_name 升序或是降许
     * @param column  按哪一行进行排序
     * @param model
     * @return
     */
    @RequestMapping("/fee_order")
    public String fee_order(Integer pageNum,String sort_name,String column,Model model){
        model.addAttribute("sort_name",sort_name);
        model.addAttribute("column",column);
        if (pageNum==null){
            pageNum = 1;
        }
        String condition = sort_name.split("_")[1];
        PageBean<Cost> pageBean = costService.findByOrder(pageNum, pageSize, condition, column);
        if (column.equals("base_cost")) {
            model.addAttribute("cost_sort", sort_name);
        } else {
            model.addAttribute("date_sort", sort_name);
        }
        model.addAttribute("pageBean",pageBean);
        return "fee/fee_list";
    }


    /**
     * 添加数据校验
     * @param cost
     * @param result
     * @return
     */
    private AjaxResult resultMap(Cost cost, BindingResult result) {
        AjaxResult ajaxCostResult = new AjaxResult();
        Map<String, Object> maps = new HashMap<String, Object>();
        if (cost.getCost_type().equals("2")) {
            if (cost.getBase_duration() < 1 || cost.getBase_duration() > 600) {
                maps.put("durationmsg", "1-600之间的整数");
                ajaxCostResult.setCount(0);
            }
            if (cost.getBase_cost() == 0 || 99999.99 < cost.getBase_cost()) {
                maps.put("costmsg", "0-99999.99之间的数值");
                ajaxCostResult.setCount(0);
            }
            if (cost.getUnit_cost() == 0 || 99999.99 < cost.getUnit_cost()) {
                maps.put("ucostmsg", "0-99999.99之间的数值");
                ajaxCostResult.setCount(0);
            }
        }


        if (cost.getCost_type().equals("1")) {
            if (cost.getBase_cost() == 0 || 99999.99 < cost.getBase_cost()) {
                maps.put("costmsg", "0-99999.99之间的数值");
                ajaxCostResult.setCount(0);
            }
        }


        if (cost.getCost_type().equals("3")) {
            if (cost.getUnit_cost() == 0 || 99999.99 < cost.getUnit_cost()) {
                maps.put("ucostmsg", "0-99999.99之间的数值");
                ajaxCostResult.setCount(0);
            }
        }
        FieldError nameErr = result.getFieldError("name");
        FieldError descrErr = result.getFieldError("descr");

        if (nameErr != null) {
            maps.put("namemsg", nameErr.getDefaultMessage());
            ajaxCostResult.setCount(0);
        }
        if (descrErr != null) {
            maps.put("descmsg", descrErr.getDefaultMessage());
            ajaxCostResult.setCount(0);
        }
        ajaxCostResult.setMaps(maps);
        return ajaxCostResult;
    }

}
