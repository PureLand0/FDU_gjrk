package com.lab1.model;
import com.lab1.print.IndentViewer;
import com.lab1.print.Viewer;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class HTMLInitTest {
    private HTML html = new HTML();
    Viewer viewer;

    @Test
    public void testInit() {
        html.init();
        HTML temp = new HTML();
        temp.read("src\\test\\resources\\HTMLTemplate.html");
        viewer = new IndentViewer(html.getRoot());
        Viewer viewer1 = new IndentViewer(temp.getRoot());
        assertEquals(new String(viewer.show()),new String(viewer1.show()));

    }
}
