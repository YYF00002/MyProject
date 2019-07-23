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
 * createDate和UpdateDate设置默认时间
 * 需在实体类上加上注解 @EntityListeners(AuditingEntityListener.class)
 * 也可使用Hibernate提供的@UpdateTimestamp 和 @CreationTimestamp注解
 */
@Entity
@Table(appliesTo = "country_area_code", comment = "国统局区域代码")
@EntityListeners(AuditingEntityListener.class)
@Data
@Where(clause = "delete_flag=0")
public class CountryAreaCode {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "code", nullable = true, columnDefinition = "varchar(50) COMMENT '区域代码'")
    private String code;

    @Column(name = "name", nullable = true, columnDefinition = "varchar(100) COMMENT '区域名称'")
    private String name;

    @Column(name = "parent_id", nullable = false, columnDefinition = "int(11) COMMENT '父级id'")
    private Integer parentId;

    @Column(name = "parent_code", nullable =false , columnDefinition = "varchar(50) COMMENT '上级区域代码'")
    private String parentCode;

    @Column(name = "level", nullable = false, columnDefinition = "varchar(20) COMMENT '层级'")
    private String level;

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
