package com.lab1.util;

import com.lab1.model.HTML;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HTMLParserTest {
    @Test
    public void testParseHTML() throws IOException {
        HTML html=HTMLParser.parseHTML("src\\test\\resources\\TestTemplate.html");
        System.out.println("html = " + html);
    }
    @Test
    public void testParseInitHTML() throws IOException{
        HTML html=HTMLParser.parseHTML("src\\test\\resources\\HTMLTemplate.html");
        System.out.println("html = " + html);
    }
    @Test
    public void testHTMLPathNotFound() throws IOException {

        String invalidFilePath = "src\\test\\resources\\testfile\\non_existent_file.html";
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> {
            HTMLParser.parseHTML("invalidFilePath");
        });
        System.out.println("exception.getMessage() = " + exception.getMessage());
        assertEquals("invalidFilePath (系统找不到指定的文件。)", exception.getMessage());
    }




}
