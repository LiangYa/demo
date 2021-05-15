package com.example.demo.service.impl;

import com.example.demo.dao.DeptAdjustSalaryDao;
import com.example.demo.entity.DeptAdjustSalary;
import com.example.demo.service.IDeptAdjustSalaryService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Service("deptAdjustSalaryService")
public class DeptAdjustSalaryServiceImpl implements IDeptAdjustSalaryService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private DeptAdjustSalaryDao deptAdjustSalaryDao;

    @Override
    public List selectAdjustSalaryHistoryByUserId(String userId) {
        String sql = "";
        if (!StringUtils.isEmpty(userId)){
            sql="SELECT * FROM testspringboot.`username` WHERE `name`='"+userId+"';";
        }
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    @Override
    public List<DeptAdjustSalary> saveDeptAdjustSalary(List<DeptAdjustSalary> deptAdjustSalaries) {
        if (CollectionUtils.isEmpty(deptAdjustSalaries)) return null;
        if (StringUtils.isEmpty(deptAdjustSalaries.get(0).getAdjustSalaryId())) return null;
        Long adjustSalaryId = deptAdjustSalaries.get(0).getAdjustSalaryId();
        for (int i=0;i<deptAdjustSalaries.size();i++){
            DeptAdjustSalary salary = deptAdjustSalaries.get(i);
            if (StringUtils.isEmpty(salary.getId())) {
                // 保存创建时间
                salary.setCreateTime(new Date());
            } else {
                DeptAdjustSalary adjust = deptAdjustSalaryDao.getOne(salary.getId());
                salary.setCreateTime(adjust.getCreateTime());
                // 保存修改人时间
                salary.setMendTime(new Date());
            }
            deptAdjustSalaries.set(i,salary);
        }
        //删除历史
        deptAdjustSalaryDao.deleteAllByAdjustSalaryId(adjustSalaryId);
        deptAdjustSalaryDao.saveAll(deptAdjustSalaries);
        return deptAdjustSalaries;
    }
}
