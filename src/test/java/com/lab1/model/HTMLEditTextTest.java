package com.lab1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HTMLEditTextTest {
    private HTML html = new HTML();

    @BeforeEach
    void setUp() {
        this.html.read("src\\test\\resources\\TestTemplate.html");
    }

    @Test
    public void testEditIdNormal() {
        String oldId = "item1";
        String text = "Item";
        html.editText(oldId,text);
        assertEquals(text, html.getMap().get(oldId).getText());

    }


    @Test
    public void testEditIdOldIdNotFound() {
        String oldId = "notfound";
        String text = "Item";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            html.editText(oldId,text);
        });
        assertEquals("没有你要找的元素", exception.getMessage());
    }

}
