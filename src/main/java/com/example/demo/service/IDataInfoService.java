package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface IDataInfoService {
    public List getList();

    /**
     * 判断是否可以上线
     * @param bpModel
     * @return
     */
    public JSONObject adjustByBpModel(String bpModel);

    /**
     * 判断是否
     * @param type 1已经上线，2可以上线，3不可以上线
     * @return
     */
    public JSONArray getBpModelListByType(Integer type);
}
