package com.lab1.command;

import com.lab1.command.edit.AppendCommand;
import com.lab1.command.edit.InsertCommand;
import com.lab1.model.HTML;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandInvokerTest {
    private CommandInvoker commandInvoker = new CommandInvoker();
    private Command command;
    private HTML html = new HTML();
    ;

    @BeforeEach
    void setUp() {
        this.html.read("src\\test\\resources\\TestTemplate.html");
        this.command = new AppendCommand("footer", "br", "br1", "Hello world!", this.html);
    }

    @Test
    void testStoreAndExecute() {
        //测试存储并执行
        this.commandInvoker.storeAndExecute(this.command);
        //查看html
        System.out.println("--------在id=footer标签内加入<br>Hello world!</br>后--------");
        this.html.printIndent(2);
    }

    @Test
    void testUndoLastCommand() {
        //测试撤销
        //先执行一条命令并查看结果
        this.testStoreAndExecute();
        //撤销
        this.commandInvoker.undoLastCommand();
        //再次查看结果
        System.out.println("----------------测销后----------------");
        this.html.printIndent(2);
    }

    @Test
    void testRedoLastCommand() {
        //测试恢复撤销
        //先撤销一条命令
        this.testUndoLastCommand();
        //恢复撤销
        this.commandInvoker.redoLastCommand();
        //查看结果
        System.out.println("----------------恢复测销后----------------");
        this.html.printIndent(2);
    }
}
