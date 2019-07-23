package com.project.springbootvideo.mappers;

import org.apache.ibatis.annotations.Param;

public interface FileMapper {
    void insertFile(@Param("fileName") String fileName,@Param("url") String url);
}
