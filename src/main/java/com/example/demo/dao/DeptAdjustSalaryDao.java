package com.example.demo.dao;

import com.example.demo.entity.DeptAdjustSalary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptAdjustSalaryDao extends JpaRepository<DeptAdjustSalary,Long> {
    List<DeptAdjustSalary> findByAdjustSalaryId(Long adjustSalaryId);
    void deleteAllByAdjustSalaryId(Long adjustSalaryId);
}
