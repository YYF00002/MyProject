package com.project.springbootvideo.controller;


import com.project.springbootvideo.service.IFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class FileOperationController {

    @Autowired
    private IFile ifile;

    @RequestMapping(method = RequestMethod.POST,path = "/uploadFile")
    public String uploadFile(MultipartFile file){

       return ifile.insertFile(file);
    }
}
