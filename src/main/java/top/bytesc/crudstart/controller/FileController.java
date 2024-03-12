package top.bytesc.crudstart.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.bytesc.crudstart.models.Result;
import top.bytesc.crudstart.utils.AliOssUtil;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        String originalFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString()+"_"+originalFileName;
        //需要绝对路径
//        file.transferTo(new File("C:\\Users\\RCY\\Desktop\\3\\"+fileName));
        String url =  AliOssUtil.UploadFile(fileName,file.getInputStream());
        return Result.success(url);
    }
}
