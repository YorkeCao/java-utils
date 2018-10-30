package io.yorkecao.utils;

import org.junit.Test;

import java.io.IOException;

public class ZipUtilsTest {

    @Test
    public void zipFiles() throws IOException {
        // 压缩不打包
        ZipUtils.zipFiles("D:/dir/test1.zip", null, "D:/dir/test1.txt");
        // 打包并压缩
        ZipUtils.zipFiles("D:/dir/test.zip", "test", "D:/dir/test1.txt", "D:/dir/test2.txt");
    }
}