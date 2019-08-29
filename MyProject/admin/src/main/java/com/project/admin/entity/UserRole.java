package com.project.admin.entity;

import lombok.Data;
import org.hibernate.annotations.Table;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 用户角色表
 * @Author: YYF
 * @CreateDate: 2019/7/24 16:47
 * @Version: 1.0
 */
@Entity
@Table(appliesTo = "user_role", comment = "用户角色表")
@EntityListeners(AuditingEntityListener.class)
@Data
@Where(clause = "delete_flag=0")
public class UserRole {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "role_name", nullable = false, columnDefinition = "varchar(20) COMMENT '角色名称'")
    private String roleName;
    @Column(name = "role_desc", columnDefinition = "varchar(255) COMMENT '角色说明'")
    private String roleDesc;
    @Column(name = "role_code", columnDefinition = "varchar(20) COMMENT '角色编码'")
    private String roleCode;

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


    @ManyToMany(mappedBy = "userRoles")
    Set<UserBaseInformation> userBaseInformations = new HashSet<>();

    @Transient//忽略于数据库的映射
    private Integer userId;
}
