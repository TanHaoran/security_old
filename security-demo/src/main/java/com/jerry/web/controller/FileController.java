package com.jerry.web.controller;

import com.jerry.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/3
 * Time: 13:50
 * Description:
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private static final String FOLDER = "E:\\IDEAProject\\SpringSecurity\\security-demo\\src\\main\\resources\\file";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(FOLDER, new Date().getTime() + ".txt");

        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (InputStream in = new FileInputStream(new File(FOLDER, id + ".txt"));
             OutputStream out = response.getOutputStream()) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(in, out);

            out.flush();
        }
    }
}
