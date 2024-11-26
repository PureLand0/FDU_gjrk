package com.lab1.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HTMLDeleteTest {
    private HTML html = new HTML();

    @BeforeEach
    void setUp() {
        this.html.read("src\\test\\resources\\TestTemplate.html");
    }
    @Test
    public void deleteNormal() {
        // 假设 'content' 是 map 中的一个有效 ID
        String elementId = "footer";
        List<HTMLTag> delete = html.delete(elementId);

        assertNotNull(delete.get(0));
        assertEquals(elementId, delete.get(0).getId());
        assertTrue(delete.get(0).isDeleted());

        // 确保 map 中不再存在该元素
        assertNull(html.findTagById(elementId));
    }

    @Test
    public void testDeleteLocationNotFound() {
        // 假设 'nonexistent' 不是 map 中的 ID
        String nonExistentId = "nonexistent";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            List<HTMLTag> delete = html.delete(nonExistentId);
        });
        assertEquals("没有你要找的元素", exception.getMessage());
    }


}
