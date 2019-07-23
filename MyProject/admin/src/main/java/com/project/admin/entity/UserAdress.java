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
@Table(appliesTo = "user_adress", comment = "用户地址表")
@EntityListeners(AuditingEntityListener.class)
@Data
@Where(clause = "delete_flag=0")
public class UserAdress {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "user_id", nullable = true, columnDefinition = "int(11) COMMENT '用户id'")
    private Integer userId;

    @Column(name = "adress", nullable = true, columnDefinition = "varchar(255) COMMENT '用户地址'")
    private String adress = "0";

    @Column(name = "description", nullable = true, columnDefinition = "varchar(255) COMMENT '描述'")
    private String description;

    @Column(name = "province_code", nullable =true , columnDefinition = "varchar(20) COMMENT '省份code'")
    private String provinceCode;

    @Column(name = "city_code", nullable = true, columnDefinition = "varchar(20) COMMENT '城市code'")
    private String cityCode;

    @Column(name = "is_default", nullable = true, columnDefinition = "int(1) COMMENT '是否是默认地址'")
    private Integer  isDefault=0;

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
