package com.project.springbootjpa.entity;


import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(appliesTo = "integral_record",comment = "用户积分记录表")
@EntityListeners(AuditingEntityListener.class)
public class IntegralRecord {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "bigInt(20) COMMENT '用户id' ")
    private Long uid;

    @Column(columnDefinition = "varchar(255) COMMENT '描述'")
    private String description;

    @Column(columnDefinition = "varchar(20) COMMENT '积分数量'")
    private String integral;

//    @ManyToOne
//    private UserBaseInformation user;


    @CreatedDate
//    @CreationTimestamp
    @Column(columnDefinition = "datetime COMMENT '创建时间'")
    private Date createDate;


    @LastModifiedDate
//    @UpdateTimestamp
    @Column(columnDefinition = "datetime COMMENT '修改时间'")
    private Date updateDate;

    @Column(columnDefinition = "varchar(1) COMMENT '逻辑删除  0-正常   1-删除'")
    private String deleteFla = "0";

    @Column(columnDefinition = "bigInt(1) COMMENT '逻辑删除  0-正常   1-删除'")
    @Version
    private Long version;
}
