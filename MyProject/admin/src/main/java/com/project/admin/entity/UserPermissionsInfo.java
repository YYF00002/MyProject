package com.project.admin.entity;

import lombok.Data;
import org.hibernate.annotations.Table;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 * 权限信息表
 * </p>
 *
 * @author YYF
 * @since 2018-07-31
 */
@Entity
@Table(appliesTo = "user_permissions_info", comment = "用户角色表")
@EntityListeners(AuditingEntityListener.class)
@Data
@Where(clause = "delete_flag=0")
public class UserPermissionsInfo  {


    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "permissions_code", columnDefinition = "varchar(20) COMMENT '权限编码'")
    private String permissionsCode;
    @Column(name = "permissions_name", columnDefinition = "varchar(20) COMMENT '权限名称'")
    private String permissionsName;
    @Column(name = "permissions_desc", columnDefinition = "varchar(20) COMMENT '权限描述'")
    private String permissionsDesc;
    @Column(name = "group_id", columnDefinition = "int(11) COMMENT '组织主键'")
    private Integer groupId;


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

}
