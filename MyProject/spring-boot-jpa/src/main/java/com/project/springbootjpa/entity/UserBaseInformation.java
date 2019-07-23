package com.project.springbootjpa.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@org.hibernate.annotations.Table(appliesTo = "user_base_information", comment = "用户信息表")
@EntityListeners(AuditingEntityListener.class)
public class UserBaseInformation {
    /**
     * createDate和UpdateDate设置默认时间
     * 需在实体类上加上注解 @EntityListeners(AuditingEntityListener.class)
     * 也可使用Hibernate提供的@UpdateTimestamp 和 @CreationTimestamp注解
     */


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = true, length = 20, columnDefinition = "varchar(20) COMMENT '姓名'")
    private String name;
    @Column(columnDefinition = "varchar(3) COMMENT '年龄'")
    private Integer age;
    @Column(columnDefinition = "varchar(255) COMMENT '住址'")
    private String address;
    @Column(columnDefinition = "varchar(1) COMMENT '性别: 0:男  1:女'")
    private String sex = "0";

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
//    private List<IntegralRecord> records;


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
