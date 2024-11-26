package com.lab1.model;

import com.lab1.print.IndentViewer;
import com.lab1.print.Viewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HTMLSaveTest {
    private HTML html = new HTML();
    Viewer viewer;

    @BeforeEach
    public void setUp() throws IOException {
        this.html.read("src\\test\\resources\\TestTemplate.html");

    }
    @Test
    public void testSaveNullRoot() {
        html.setRoot(null);
        // 尝试插入一个具有相同ID的标签，应该抛出IllegalArgumentException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            html.save("src\\test\\resources");
        });
        assertEquals("该html为空", exception.getMessage());
    }

    @Test
    public void testSaveValid() {
        String directoryPath = "src\\test\\resources";
        String path = html.save(directoryPath);
        HTML temp = new HTML();
        temp.read(path);
        viewer = new IndentViewer(html.getRoot());
        Viewer viewer1 = new IndentViewer(temp.getRoot());
        assertEquals(new String(viewer.show()),new String(viewer1.show()));

    }


}
