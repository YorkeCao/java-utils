package io.yorkecao.utils;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class FileUtilsTest {

    @Test
    public void createFile() throws IOException {
        String file = "D:/test/tt/test.txt";
        FileUtils.createFile(file, "tesss");
    }

    @Test
    public void StringJoinTest() {
        System.out.println(String.join("", "A", null, "B"));
    }

    @Test
    public void forEachTest() {
        List<String> strings = null;

        Optional.ofNullable(strings)
                .ifPresent(s -> s.forEach(System.out::println));
    }
}