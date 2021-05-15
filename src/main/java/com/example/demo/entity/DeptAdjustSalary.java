package com.example.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "dept_adjust_salary") // 部门人员调薪申请主表
public class DeptAdjustSalary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "bigint COMMENT '主键'")
    private Long Id;

    @Column(columnDefinition = "bigint COMMENT '调薪外键'")
    private Long adjustSalaryId;

    @Column(columnDefinition = "bigint COMMENT '调薪详细外键'")
    private Long adjustSalaryDetailId;

    @Column(columnDefinition = "varchar(100) COMMENT '员工工号'")
    private String employeeId;

    @Column(columnDefinition = "varchar(100) COMMENT '员工姓名'")
    private String employeeName;

    @Column(columnDefinition = "decimal(19,0) COMMENT '当前工资(元)'")
    private Double currentSalary;

    @Column(columnDefinition = "decimal(19,0) COMMENT '调整后工资(元)'")
    private Double adjustedSalary;

    @Column(columnDefinition = "decimal(19,0) COMMENT '薪资调整比例'")
    private Double adjustProportion;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "datetime COMMENT '创建时间'")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "datetime COMMENT '修改时间'")
    private Date mendTime;


}
