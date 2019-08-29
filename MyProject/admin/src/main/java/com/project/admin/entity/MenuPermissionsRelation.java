package com.project.admin.entity;

import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 * 菜单权限关联表
 * </p>
 *
 * @author YYF
 * @since 2018-07-31
 */
@Entity
@org.hibernate.annotations.Table(appliesTo = "menu_permissions_relation", comment = "菜单权限关联表")
@EntityListeners(AuditingEntityListener.class)
@Data
@Where(clause = "delete_flag=0")
public class MenuPermissionsRelation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "menu_id", nullable = false, columnDefinition = "int(11) COMMENT '菜单主键id'")
    private Integer menuId;
    @Column(name = "permissions_id", nullable = false, columnDefinition = "int(11) COMMENT '权限主键id'")
    private Integer permissionsId;
    @Column(name = "status", columnDefinition = "int(11) COMMENT '状态（根据需要使用）'")
    private Integer status;

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
