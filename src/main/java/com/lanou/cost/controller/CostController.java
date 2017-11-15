package com.lanou.cost.controller;

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
    public AjaxResult feeAdd(@Validated Cost cost, BindingResult br){
        AjaxResult<Cost> result = new AjaxResult<Cost>();
        Map<String,Object> maps = new HashMap<String, Object>();

        if (br.hasErrors()){
            FieldError nameError = br.getFieldError("name");
            FieldError base_durationError = br.getFieldError("base_duration");
            FieldError base_costError = br.getFieldError("base_cost");
            FieldError unit_costError = br.getFieldError("unit_cost");
            FieldError descrError = br.getFieldError("descr");

            if (nameError!=null){
               maps.put("namemsg",nameError.getDefaultMessage());
            }
            System.out.println(maps);
//            if (base_durationError!=null){
//                maps.put("durationmsg",base_durationError.getDefaultMessage());
//            }
//            if (base_costError!=null){
//               maps.put("costmsg",base_costError.getDefaultMessage());
//            }
//            if (unit_costError!=null){
//               maps.put("ucostmsg",base_costError.getDefaultMessage());
//            }
//            if (descrError!=null){
//               maps.put("descmsg",descrError.getDefaultMessage());
//            }
        }

        if (maps.size()>0){
            result.setMaps(maps);
            result.setCount(0);

        }else {
            cost.setStatus("0");
            cost.setCreatTime(new Timestamp(System.currentTimeMillis()));
            int count = costService.save(cost);
            result.setCount(count);
        }

        return result;

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
     * 修改
     * @param cost
     * @return
     */
    @ResponseBody
    @RequestMapping("/updById")
    public AjaxResult<Cost> updById(Cost cost){
        AjaxResult<Cost> result = new AjaxResult<Cost>();
        System.out.println(cost);
        int count = costService.updateById(cost);
        System.out.println(count);
        result.setCount(count);
        return result;
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

}
