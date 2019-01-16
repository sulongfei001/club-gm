package com.sevenXnetworks.treasure.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Description 测试工具类
 * @Author sulongfei
 * @Date 2018/11/20 16:16
 * @Version 1.0
 */
public class TestUtils {
    public static void main(String[] args) {

    }

    public static void writeFile(String str) throws IOException {
        FileWriter fw = new FileWriter("/temp/file/log.log", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("\r\n");
        bw.write(str);
        bw.close();
        fw.close();
    }
}
