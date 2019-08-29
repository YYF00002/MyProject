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
 * 用户菜单信息表
 * </p>
 *
 * @author YYF
 * @since 2018-07-31
 */
@Entity
@org.hibernate.annotations.Table(appliesTo = "user_menu_info", comment = "用户菜单信息表")
@EntityListeners(AuditingEntityListener.class)
@Data
@Where(clause = "delete_flag=0")
public class UserMenuInfo {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "menu_code", columnDefinition = "varchar(20) COMMENT '权限编码'")
    private String menuCode;

    @Column(name = "menu_name",nullable = false, columnDefinition = "varchar(20) COMMENT '菜单名称'")
    private String menuName;

    @Column(name = "menu_parent_id", columnDefinition = "int(11) COMMENT '上级菜单id'")
    private Integer menuParentId;

    @Column(name = "order_num", columnDefinition = "varchar(20) COMMENT '内部序号供排序使用'")
    private String orderNum;

    @Column(name = "menu_url", columnDefinition = "varchar(255) COMMENT '菜单url地址'")
    private String menuUrl;

    @Column(name = "is_end",nullable = false,columnDefinition = "char(1) COMMENT '是否末级 Y为末级 N或者空为非末级 默认为N(根据需要使用)'")
    private String isEnd="N";

    @Column(name = "menu_level", columnDefinition = "int(20) COMMENT '菜单级别'")
    private Integer menuLevel;

    @Column(name = "menu_ico", columnDefinition = "varchar(255) COMMENT '菜单图标'")
    private String menuIco;

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
