package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

    @Override
    public String toString() {
        return "DataInfo{" +
                "tableId=" + tableId +
                ", bpModel='" + bpModel + '\'' +
                ", dep='" + dep + '\'' +
                ", parent=" + parent +
                ", model_is_online=" + model_is_online +
                ", dep_is_online=" + dep_is_online +
                '}';
    }
}
