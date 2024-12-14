package com.peng.springbootmall.util;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileDo {

    public static void processFile(String inputFilePath, String outputFilePath) {
        List<String> processedLines = new ArrayList<>();

        // 讀取檔案
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 每行加密
                String md5Hash = "md5Hash";
                // 將 MD5 結果追加到該行
                processedLines.add(line + " " + md5Hash);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + inputFilePath, e);
        }

        // 將結果寫入新檔案
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
            for (String processedLine : processedLines) {
                writer.write(processedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + outputFilePath, e);
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "input.txt"; // 本地檔案路徑
        String outputFilePath = "output.txt"; // 新檔案路徑

        processFile(inputFilePath, outputFilePath);
        System.out.println("檔案處理完成，結果已輸出到：" + outputFilePath);
    }
}

