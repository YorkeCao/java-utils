package io.yorkecao.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Yorke
 */
public class FileUtils {

    /**
     * 创建文件并写入内容
     * @param file 文件路径
     * @param content 文件内容
     */
    public static void createFile(String file, String content) throws IOException {
        Path filePath = Paths.get(file);
        if (!Files.exists(filePath)) {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            Files.createFile(filePath);
        }
        if (content != null) {
            Files.write(filePath, content.getBytes());
        }
    }
}
