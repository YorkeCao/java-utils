package io.yorkecao.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Yorke
 */
public class ZipUtils {

    /**
     * 压缩文件
     * @param zipFile 压缩文件路径
     * @param baseDir 打包目录
     * @param files 待打包文件或目录
     */
    public static void zipFiles(String zipFile, String baseDir, String... files) throws IOException {
        FileUtils.createFile(zipFile, null);
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             CheckedOutputStream cos = new CheckedOutputStream(fos, new CRC32());
             ZipOutputStream zos = new ZipOutputStream(cos)) {

            baseDir = Optional.ofNullable(baseDir)
                    .filter(d -> !"/".equals(d))
                    .map(d -> d.endsWith("/") ? d : d + "/")
                    .orElse("");

            for (String file : files) {
                Path filePath = Paths.get(file);
                if (Files.exists(filePath)) {
                    compress(filePath, zos, baseDir);
                } else {
                    throw new FileNotFoundException(file);
                }
            }
        }
    }

    /**
     * 压缩目录或文件
     */
    private static void compress(Path filePath, ZipOutputStream zos, String baseDir) throws IOException {
        if (Files.isDirectory(filePath)) {
            // 压缩目录
            for (Path subFilePath : Files.list(filePath).collect(Collectors.toList())) {
                compress(subFilePath, zos, baseDir + filePath.getFileName().toString() + "/");
            }
        } else {
            // 压缩文件
            ZipEntry entry = new ZipEntry(baseDir + filePath.getFileName().toString());
            zos.putNextEntry(entry);
            zos.write(Files.readAllBytes(filePath));
        }
    }
}
