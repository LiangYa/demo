package com.example.demo.ctrl;

import com.alibaba.fastjson.JSON;
import com.example.demo.service.IDataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liangya
 * @date 2021/5/15 12:47
 */
@Controller
@RequestMapping("dataInfo")
public class DataInfoController {
    @Autowired
    private IDataInfoService dataInfoService;

    /**
     * 判断是否可以上线
     * @param bpModel
     * @return
     */
    @RequestMapping("/adjustByBpModel")
    @ResponseBody
    public String adjustByBpModel(String bpModel){
        Map<String,Object> result = new HashMap<>();
        if (!StringUtils.isEmpty(bpModel)){
            result.put("list", dataInfoService.adjustByBpModel(bpModel));
            result.put("status",0);
        }else {
            result.put("status",-1);
            result.put("msg","数据查询失败");
        }
        return JSON.toJSONString(result);
    }

    @RequestMapping("/getBpModelListByType")
    @ResponseBody
    public String getBpModelListByType(Integer type){
        Map<String,Object> result = new HashMap<>();
        if (type != null){
            List list = dataInfoService.getBpModelListByType(type);
            result.put("list", list);
            result.put("status", list.size());
        }else {
            result.put("status",-1);
            result.put("msg","数据查询失败");
        }
        return JSON.toJSONString(result);
    }
}
