package com.lab1.print;

import com.lab1.model.HTML;
import com.lab1.model.HTMLTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndentViewerShowTest {
    private HTML html = new HTML();
    private Viewer viewer;


    @BeforeEach
    void setUp() {
        this.html.read("src\\test\\resources\\TestTemplate.html");
    }

    @Test
    void testIndentShowHTML() {
        // 测试正常缩进显示
        if (html.getRoot() == null) {
            throw new RuntimeException("当前html为空");
        }
        viewer = new IndentViewer(html.getRoot(), 2);
        System.out.println(new String(viewer.show()));
    }
}
