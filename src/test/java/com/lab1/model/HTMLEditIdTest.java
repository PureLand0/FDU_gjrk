package com.lab1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HTMLEditIdTest {
    private HTML html = new HTML();

    @BeforeEach
    void setUp() {
        this.html.read("src\\test\\resources\\TestTemplate.html");
    }

    @Test
    public void testEditIdNormal() {
        String oldId = "item1";
        String newId = "item4";
        html.editId(oldId, newId);
        assertEquals(newId, html.getMap().get(newId).getId());

    }

    @Test
    public void testEditIdOldIdDuplicate() {
        String oldId = "item1";
        String newId = "item2";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            html.editId(oldId, newId);
        });
        assertEquals("ID重复", exception.getMessage());

    }

    @Test
    public void testEditIdOldIdNotFound() {
        String oldId = "notfound";
        String newId = "item2";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            html.editId(oldId, newId);
        });
        assertEquals("没有你要找的元素", exception.getMessage());
    }

}
