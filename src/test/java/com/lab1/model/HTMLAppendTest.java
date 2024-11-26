package com.lab1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class HTMLAppendTest {
    private HTML html = new HTML();

    @BeforeEach
    void setUp() {
        this.html.read("src\\test\\resources\\TestTemplate.html");
    }

    @Test
    void testInsertValidLeafTag() {
        // HTMLLeafTag是一个叶子标签类
        html.append("img", "img1", "footer", "");

        // 验证是否插入成功
        assertTrue(html.getMap().containsKey("img1"));
        HTMLTag insertedTag = html.getMap().get("img1");
        assertEquals("img", insertedTag.getName());
        assertEquals("", insertedTag.getText());
    }

    @Test
    void testInsertValidCompositeTag() {
        // HTMLCompositeTag是一个组合标签类
        html.append("div", "div1", "footer", "Another div");
        // 验证是否插入成功
        assertTrue(html.getMap().containsKey("div1"));
        HTMLTag insertedTag = html.getMap().get("div1");
        assertEquals("div", insertedTag.getName());
        assertEquals("Another div", insertedTag.getText());
    }

    @Test
    void testInsertDuplicateIDThrowsException() {
        // 尝试插入一个具有相同ID的标签，应该抛出IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            html.append("div", "footer", "footer", "Text content");
        });

        assertEquals("ID重复", exception.getMessage());
    }

    @Test
    void testInsertInvalidTagThrowsException() {
        // 使用一个非法的标签名
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            html.append("invalidTag", "tag3", "footer", "Invalid tag content");
        });

        assertEquals("标签名非法", exception.getMessage());
    }

    @Test
    void testInsertLocationNotFound() {
        // 使用一个不存在的插入位置
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            html.append("div", "tag4", "nonExistentTag", "");
        });
        assertEquals("你插入的位置不存在", exception.getMessage());
    }

    @Test
    void testInsertLocationNotCompeosite() {
        // 往叶子结点插入
        html.insert("img", "img1", "footer", "leaf img");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            html.append("div", "div1", "img1", "");
        });
        assertEquals("目标父元素内不可加入元素", exception.getMessage());
    }
}
