package com.example.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "data_info")
public class DataInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "int COMMENT '主键'")
    private Integer Id;

    @Column(columnDefinition = "int COMMENT '序号'")
    private Integer tableId;

    @Column(columnDefinition = "varchar(500) COMMENT ''")
    private String bpModel;

    @Column(columnDefinition = "varchar(500) COMMENT ''")
    private String dep;

    @Column(columnDefinition = "int COMMENT 'parent_id'")
    private Integer parent;

    @Column(columnDefinition = "int COMMENT '是否上线'")
    private Integer model_is_online;

    @Column(columnDefinition = "int COMMENT '父类是否上线'")
    private Integer dep_is_online;
}
