package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
public class UploadController {

    private final static String FILE_UPLOAD_PATH = "F:/projects/demoSpringBoot/upload/";
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return "Fail upload.";
        }
        String fileName = file.getOriginalFilename();
        // delete the filename extension
        // - .txt
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // a common way to generate a new file name
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random random = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(simpleDateFormat.format(new Date())).append(random.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();

        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FILE_UPLOAD_PATH + newFileName);
            Files.write(path, bytes);
        } catch (IOException e){
            e.printStackTrace();
        }
        return "file uploaded. Address: /files/" + newFileName;
    }
}
