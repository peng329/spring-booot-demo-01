package com.peng.springbootmall.controller;

import com.peng.springbootmall.util.FileDo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    //讀檔編輯後存新檔
    //注意用 POST，且用 @RequestParam，不能用 json
    @PostMapping("/file")
    ResponseEntity <String> fileEdit(@RequestParam String inputFilePath, @RequestParam String outputFilePath){
        System.out.println(inputFilePath);
        System.out.println(outputFilePath);
        FileDo.processFile(inputFilePath, outputFilePath);
        return ResponseEntity.ok("檔案處理完成，結果已輸出到：" + outputFilePath);
    }
}
