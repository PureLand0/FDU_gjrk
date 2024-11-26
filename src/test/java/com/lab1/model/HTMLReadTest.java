package com.lab1.model;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class HTMLReadTest {
    private HTML html = new HTML();


    @Test
    void testValidFilePath() throws IOException {
        //正确地址
        this.html.read("src\\test\\resources\\TestTemplate.html");
        assertNotNull(html.getRoot(), "Root element should not be null");
        assertFalse(html.getMap().isEmpty(), "ID to Tag map should not be empty");
    }

    @Test
    void testInvalidFilePath() {
        //不正确地址
        String invalidFilePath = "src\\test\\resources\\testfile\\non_existent_file.html";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            html.read(invalidFilePath);
        });

        assertEquals("请确认该地址是否存在文件", exception.getMessage());
    }
}
