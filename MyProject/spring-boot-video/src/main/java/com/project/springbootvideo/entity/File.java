package com.project.springbootvideo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class File {

    private  int id ;
    private String fileName;
    private String fileType;
    private String url;
    private Date createTime;
    private Date lastModifiedDate;
    private String deleteFlag;

}
