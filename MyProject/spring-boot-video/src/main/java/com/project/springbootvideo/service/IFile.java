package com.project.springbootvideo.service;

import org.springframework.web.multipart.MultipartFile;
public interface IFile {

    String insertFile(MultipartFile file);
}
