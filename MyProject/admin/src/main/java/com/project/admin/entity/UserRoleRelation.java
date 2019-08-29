//package com.project.admin.entity;
//
//import lombok.Data;
//import org.hibernate.annotations.Table;
//import org.hibernate.annotations.Where;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import java.util.Date;
//
///**
// * <p>
// * 用户角色关联表
// * </p>
// *
// * @author YYF
// * @since 2018-07-31
// */
//@Entity
//@Table(appliesTo = "user_role_relation", comment = "用户角色关联表")
//@EntityListeners(AuditingEntityListener.class)
//@Data
//@Where(clause = "delete_flag=0")
//public class UserRoleRelation  {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue
//    private Integer id;
//    @Column(name = "user_id", nullable = false, columnDefinition = "int(11) COMMENT '用户id'")
//    private Integer userId;
//    @Column(name = "user_role_id", nullable = false, columnDefinition = "int(11) COMMENT '用户角色id'")
//    private Integer userRoleId;
//    @Column(name = "status", columnDefinition = "int(11) COMMENT '状态'")
//    private Integer status;
//
//    @CreatedDate
//    @Column(columnDefinition = "datetime COMMENT '创建时间'")
//    private Date createDate;
//    @LastModifiedDate
//    @Column(columnDefinition = "datetime COMMENT '修改时间'")
//    private Date lastModifiedDate;
//    @Version
//    @Column(columnDefinition = "int(11) COMMENT '版本号'")
//    private Integer version;
//    @Column(columnDefinition = "int(1) COMMENT '逻辑删除  0-正常   1-删除'")
//    private Integer deleteFlag = 0;
//
//}
