package com.lab1.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class TagEnumTest {
    @Test
    void testIsLeafTag() {
        assertTrue(TagEnum.isLeafTag("img"));
        assertTrue(TagEnum.isLeafTag("br"));
        assertTrue(TagEnum.isLeafTag("hr"));
        assertTrue(TagEnum.isLeafTag("input"));
        assertTrue(TagEnum.isLeafTag("link"));
        assertTrue(TagEnum.isLeafTag("meta"));
        assertTrue(TagEnum.isLeafTag("area"));
        assertTrue(TagEnum.isLeafTag("base"));
        assertTrue(TagEnum.isLeafTag("col"));
        assertTrue(TagEnum.isLeafTag("command"));
        assertTrue(TagEnum.isLeafTag("embed"));
        assertTrue(TagEnum.isLeafTag("keygen"));
        assertTrue(TagEnum.isLeafTag("param"));
        assertTrue(TagEnum.isLeafTag("source"));
        assertTrue(TagEnum.isLeafTag("track"));
        assertTrue(TagEnum.isLeafTag("wbr"));
        assertTrue(TagEnum.isLeafTag("title"));
    }

    @Test
    void testIsCompositeTag() {
        assertTrue(TagEnum.isCompositeTag("div"));
        assertTrue(TagEnum.isCompositeTag("span"));
        assertTrue(TagEnum.isCompositeTag("p"));
        assertTrue(TagEnum.isCompositeTag("h1"));
        assertTrue(TagEnum.isCompositeTag("h2"));
        assertTrue(TagEnum.isCompositeTag("h3"));
        assertTrue(TagEnum.isCompositeTag("h4"));
        assertTrue(TagEnum.isCompositeTag("h5"));
        assertTrue(TagEnum.isCompositeTag("h6"));
        assertTrue(TagEnum.isCompositeTag("ul"));
        assertTrue(TagEnum.isCompositeTag("ol"));
        assertTrue(TagEnum.isCompositeTag("li"));
        assertTrue(TagEnum.isCompositeTag("table"));
        assertTrue(TagEnum.isCompositeTag("tr"));
        assertTrue(TagEnum.isCompositeTag("td"));
        assertTrue(TagEnum.isCompositeTag("th"));
        assertTrue(TagEnum.isCompositeTag("form"));
        assertTrue(TagEnum.isCompositeTag("section"));
        assertTrue(TagEnum.isCompositeTag("article"));
        assertTrue(TagEnum.isCompositeTag("html"));
        assertTrue(TagEnum.isCompositeTag("head"));
        assertTrue(TagEnum.isCompositeTag("body"));
        assertTrue(TagEnum.isCompositeTag("headre"));
        assertTrue(TagEnum.isCompositeTag("footer"));
        assertTrue(TagEnum.isCompositeTag("aside"));
        assertTrue(TagEnum.isCompositeTag("nav"));
        assertTrue(TagEnum.isCompositeTag("main"));
        assertTrue(TagEnum.isCompositeTag("figure"));
        assertTrue(TagEnum.isCompositeTag("blockquote"));
    }
}
