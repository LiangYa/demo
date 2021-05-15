package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.DataInfoDao;
import com.example.demo.entity.DataInfo;
import com.example.demo.service.IDataInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author liangya
 * @date 2021/5/15 12:49
 */
@Service("dataInfoService")
public class DataInfoServiceImpl implements IDataInfoService {
    @Autowired
    private DataInfoDao dataInfoDao;
    private final static Logger logger = LoggerFactory.getLogger(DataInfoServiceImpl.class);

    @Override
    public List getList() {
        List<DataInfo> dataInfos = dataInfoDao.findAll();
        return dataInfos;
    }

    @Override
    public JSONObject adjustByBpModel(String bpModel) {
        JSONObject num = null;
        List<DataInfo> dataInfoList = dataInfoDao.findByBpModel(bpModel);
        if (dataInfoList != null && dataInfoList.size() > 0){
            num  = this.adjustByTableId(dataInfoList.get(0).getTableId());
        }
        return num;
    }

    @Override
    public JSONArray getBpModelListByType(Integer type) {
        List<DataInfo> byDepList = dataInfoDao.findByDepList();
        JSONArray array = new JSONArray();
        if (byDepList != null && byDepList.size() > 0){
            for (int i = 0; i < byDepList.size(); i++){
                DataInfo dataInfo = byDepList.get(i);
                logger.info("************"+dataInfo.toString());
                JSONObject jsonObject = this.adjustByTableId(dataInfo.getTableId());

                Integer num = jsonObject.getInteger("num");
                if (type != null && num == type){
                    jsonObject.put("model", dataInfo.getBpModel());
                    array.add(jsonObject);
                }

            }
        }
        return array;
    }

    /**
     * 查询模型是否上线
     * @param tableId
     * @return num = 1已经上线，2可以上线，3不可以上线 null没有上线或参数为空,-1已经上线， 0 可以上线 其他数字是最近未上线的依赖
     */
    public JSONObject adjustByTableId(Integer tableId) {
        JSONObject res = new JSONObject();
        Integer num = 3;
        String name = "";
        res.put("num",num);
        res.put("name",name);
        if (tableId == null) return res;
        List<DataInfo> dataInfoList = dataInfoDao.findByTableId(tableId);
        if (dataInfoList != null){
            //模型已经上线
            if (dataInfoList.get(0).getModel_is_online() != null && dataInfoList.get(0).getModel_is_online() == 1){
                logger.info(dataInfoList.get(0).getBpModel()+"已经上线");
                num = 1;
            }
            //此模型没有上线
            else {
                int count = 0;
                for (int i = 0; i < dataInfoList.size(); i++){
                    DataInfo dataInfo = dataInfoList.get(i);
                    //依赖模型已经上线
                    if (dataInfo.getDep_is_online() != null && dataInfo.getDep_is_online() == 1){
                        count++;
                        logger.info(dataInfo.getDep()+"依赖已经上线");
                    }
                    //依赖没有上线
                    else {
                        //查询依赖的上游是否上线
                        if(dataInfo.getBpModel() != null && dataInfo.getParent() != null){
                            JSONObject parentNum = adjustByTableId(dataInfo.getParent());
                            Integer pNum = parentNum.getInteger("num");
                            switch (pNum){
                                case 1:
                                    num = 3;
                                    name = dataInfo.getBpModel();
                                    count++;
                                    break;
                                case 0:
                                    num = 3;
                                    name = dataInfo.getDep();
                                    break;
                                default:
                                    num = 3;
                                    name = parentNum.getString("name");
                            }
                        } else {
                            num = 3;
                            name = dataInfo.getDep();
                        }
                        logger.info("未上线："+dataInfo.toString());
                        break;
                    }
                }
                if (count == dataInfoList.size()){
                    num = 2;
                    logger.info(dataInfoList.get(0).getBpModel()+"可以上线!");
                    name = "可以上线";
                }
            }
        }
        res.put("num",num);
        res.put("name",name);
        return res;
    }


    @Override
    public String updateStatus(String model) {
        String msg = "失败";
        if (model == null) {
            msg = "model is null!";
            logger.info(msg);
            return msg;
        }
        List<DataInfo> byBpModel = dataInfoDao.findByBpModel(model);
        List<DataInfo> byDep = dataInfoDao.findByDep(model);
        if (byBpModel == null && byDep == null){
            msg = "没有数据";
            logger.info(msg);
            return msg;
        } else {
            if (byBpModel != null && byBpModel.size() > 0){
                for (int i = 0; i < byBpModel.size(); i++){
                    DataInfo byModel = byBpModel.get(i);
                    byModel.setModel_is_online(1);
                    dataInfoDao.saveAndFlush(byModel);
                }
            }
            if (byDep != null && byDep.size() > 0){
                for (int i = 0; i < byDep.size(); i++){
                    DataInfo dep = byDep.get(i);
                    dep.setDep_is_online(1);
                    dataInfoDao.saveAndFlush(dep);
                }
            }
        }
        msg = "成功！";
        return msg;
    }

}
