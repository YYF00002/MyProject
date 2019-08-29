package com.project.admin.entity;

import lombok.Data;
import org.hibernate.annotations.Table;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;


/**
 * createDate和UpdateDate设置默认时间
 * 需在实体类上加上注解 @EntityListeners(AuditingEntityListener.class)
 * 也可使用Hibernate提供的@UpdateTimestamp 和 @CreationTimestamp注解
 */
@Entity
@Table(appliesTo = "user_base_information", comment = "用户信息表")
@EntityListeners(AuditingEntityListener.class)
@Data
@Where(clause = "delete_flag=0")
public class UserBaseInformation {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "user_name", nullable = true, columnDefinition = "varchar(20) COMMENT '用户名'")
    private String userName;
    @Column(name = "age", nullable = true, columnDefinition = "int(5) COMMENT '年龄'")
    private Integer age;
    @Column(name = "sex", nullable = true, columnDefinition = "char(1) COMMENT '性别'")
    private String sex = "0";
    @Column(name = "head_url", nullable = true, columnDefinition = "varchar(255) COMMENT '头像'")
    private String headUrl;
    @Column(name = "phone", nullable = true, columnDefinition = "varchar(11) COMMENT '手机号'")
    private String phone;
    @Column(name = "user_code", nullable = false, columnDefinition = "varchar(20) COMMENT '用户账号'", unique = true)
    private String userCode;
    @Column(name = "password", nullable = false, columnDefinition = "varchar(255) COMMENT '密码'")
    private String password;
    @CreatedDate
    @Column(columnDefinition = "datetime COMMENT '创建时间'")
    private Date createDate;
    @LastModifiedDate
    @Column(columnDefinition = "datetime COMMENT '修改时间'")
    private Date lastModifiedDate;
    @Version
    @Column(columnDefinition = "int(11) COMMENT '版本号'")
    private Integer version;
    @Column(columnDefinition = "int(1) COMMENT '逻辑删除  0-正常   1-删除'")
    private Integer deleteFlag = 0;

    //一对多
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<UserAdress> adresses = new ArrayList<UserAdress>();


    //多对多

    //使用 @ManyToMany 注解来映射多对多关联关系
    //使用 @JoinTable 来映射中间表
    //1. name 指向中间表的名字
    //2. joinColumns 映射当前类所在的表在中间表中的外键
    //2.1 name 指定外键列的列名
    //2.2 referencedColumnName 指定外键列关联当前表的哪一列
    //3. inverseJoinColumns 映射关联的类所在中间表的外键
    @JoinTable(name = "user_role_relation",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_role_id", referencedColumnName = "id")}
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>();

}
