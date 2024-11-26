package com.lab1.print;

import com.lab1.model.HTML;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeViewerShowTest {
    private HTML html = new HTML();
    private Viewer viewer;

    @BeforeEach
    void setUp() {
        this.html.read("src\\test\\resources\\TestTemplate.html");
    }

    @Test
    void testTreeShowHTML() {
        // 测试正常树形显示
        if (html.getRoot() == null) {
            throw new RuntimeException("当前html为空");
        }
        viewer = new TreeViewer(html.getRoot());
        System.out.println(new String(viewer.show()));
    }
}
