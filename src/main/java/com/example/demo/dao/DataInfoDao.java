package com.example.demo.dao;

import com.example.demo.entity.DataInfo;
import com.example.demo.entity.DeptAdjustSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author liangya
 * @date 2021/5/15 12:47
 */
public interface DataInfoDao extends JpaRepository<DataInfo, Integer> {
    List<DataInfo> findByBpModel(String bpModel);
    List<DataInfo> findByDep(String dep);
    List<DataInfo> findByTableId(Integer tableId);
    @Query(value = "SELECT * FROM data_info WHERE id IN (SELECT MAX(id)as id FROM data_info GROUP BY bp_model)", nativeQuery = true)
    List<DataInfo> findByDepList();
}
