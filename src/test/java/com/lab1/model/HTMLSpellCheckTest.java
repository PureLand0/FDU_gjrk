package com.lab1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class HTMLSpellCheckTest {
    private HTML html = new HTML();
    @BeforeEach
    public void setUp() {
        this.html.read("src\\test\\resources\\TestTemplate.html");
    }

    @Test
    public void testSpellCheck() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // 保存原始的System.out
        System.setOut(new PrintStream(outputStream));  // 重定向到ByteArrayOutputStream

        // 调用需要测试的方法
        html.spellCheck();

        // 恢复System.out，防止影响后续的输出
        System.setOut(originalOut);

        // 获取输出的内容
        String consoleOutput = outputStream.toString();

        // 检查输出内容中是否包含特定的字符串
        assertTrue("The output does not contain 'xxx'",consoleOutput.contains("Possible spell error:"));
    }
}
