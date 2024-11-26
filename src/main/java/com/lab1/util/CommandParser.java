package com.lab1.util;


import com.lab1.command.*;
import com.lab1.command.edit.*;
import com.lab1.command.show.io.InitCommand;
import com.lab1.command.show.io.ReadCommand;
import com.lab1.command.show.io.SaveCommand;
import com.lab1.command.show.io.SpellCheckCommand;
import com.lab1.command.show.PrintIndentCommand;
import com.lab1.command.show.PrintTreeCommand;
import com.lab1.model.HTML;

public class CommandParser {
    /**
     * @param cmdStr   命令行指令字符串
     * @param receiver 所有指令要统一管理的接收者html文档对象，这里解析指令并进行receiver的依赖注入
     * @return 返回解析后的指令，包括：
     * InsertCommand, AppendCommand, EditIdCommand, EditTextCommand, DeleteCommand, PrintIndentCommand
     * PrintTreeCommand, SpellCheckCommand, ReadCommand, SaveCommand, InitCommand, UndoCommand, RedoCommand
     */
    public static Command parse(String cmdStr, CommandInvoker commandInvoker, HTML receiver) { //TODO: zgn加了CommandInvoker
        Command command = null;
        String[] cmdStrings = cmdStr.split("\\s+");
        String prefix = cmdStrings[0];
        if ("insert".equals(prefix)) {
            //Format: insert tagName idValue insertLocation [textContent]
            if (cmdStrings.length >= 5) {
                StringBuilder textContent = new StringBuilder();
                for (int i = 4; i < cmdStrings.length; i++) {
                    textContent.append(" ").append(cmdStrings[i]);
                }
                command = new InsertCommand(cmdStrings[1], cmdStrings[2], cmdStrings[3], textContent.toString(),receiver);
            } else if (cmdStrings.length == 4) {
                //textContent为空表示空文本
                command = new InsertCommand(cmdStrings[1], cmdStrings[2], cmdStrings[3], "" ,receiver);
            } else {
                System.out.println("It looks like you are typing <insert> but with uncorrected format");
            }
        } else if ("append".equals(prefix)) {
            //Format: append tagName idValue parentElement [textContent]
            if (cmdStrings.length >= 5) {
                StringBuilder textContent = new StringBuilder();
                for (int i = 4; i < cmdStrings.length; i++) {
                    textContent.append(" ").append(cmdStrings[i]);
                }
                command = new AppendCommand( cmdStrings[3], cmdStrings[1], cmdStrings[2], textContent.toString(),receiver);
            } else if (cmdStrings.length == 4) {
                //如果可选选项[textContent]为空，则默认为空字符串
                command = new AppendCommand( cmdStrings[3], cmdStrings[1], cmdStrings[2],"",receiver);
            } else {
                System.out.println("It looks like you are typing <append> but with uncorrected format");
            }
        } else if ("edit-id".equals(prefix)) {
            //Format: edit-id oldId newId
            if (cmdStrings.length == 3) {
                command = new EditIdCommand(cmdStrings[1], cmdStrings[2],receiver);
            } else {
                System.out.println("It looks like you are typing <edit-id> but with uncorrected format");
            }
        } else if ("edit-text".equals(prefix)) {
            //Format: edit-text element [newTextContent]
            if (cmdStrings.length >= 3) {
                StringBuilder newTextContent = new StringBuilder();
                for (int i = 2; i < cmdStrings.length; i++) {
                    newTextContent.append(" ").append(cmdStrings[i]);
                }
                command = new EditTextCommand(cmdStrings[1], newTextContent.toString(), receiver);
            } else if (cmdStrings.length == 2) {
                //如果可选选项[newTextContent]为空，则默认为空字符串
                command = new EditTextCommand(cmdStrings[1], "", receiver);
            } else {
                System.out.println("It looks like you are typing <edit-text> but with uncorrected format");
            }
        } else if ("delete".equals(prefix)) {
            //Format: delete element
            if (cmdStrings.length == 2) {
                command = new DeleteCommand(cmdStrings[1], receiver);
            } else {
                System.out.println("It looks like you are typing <delete> but with uncorrected format");
            }
        } else if ("print-indent".equals(prefix)) {
            //Format: print-indent [indent]
            if (cmdStrings.length == 2) {
                //当提供 indent 时，使用指定的空格数进行缩进显示。
                command = new PrintIndentCommand(Integer.parseInt(cmdStrings[1]), receiver);
            } else if (cmdStrings.length == 1) {
                //每级缩进的空格数，默认为 2
                command = new PrintIndentCommand(2, receiver);
            }
        } else if ("print-tree".equals(prefix)) {
            //Format: print-tree
            command = new PrintTreeCommand(receiver);
        } else if ("spell-check".equals(prefix)) {
            //Format: spell-check
            command = new SpellCheckCommand(receiver);
        } else if ("read".equals(prefix)) {
            //Format: read filepath
            if (cmdStrings.length == 2) {
                command = new ReadCommand(cmdStrings[1], receiver);
            } else {
                System.out.println("It looks like you are typing <read> but with uncorrected format");
            }
        } else if ("save".equals(prefix)) {
            //Format: save filepath
            if (cmdStrings.length == 2) {
                command = new SaveCommand(cmdStrings[1], receiver);
            } else {
                System.out.println("It looks like you are typing <save> but with uncorrected format");
            }
        } else if ("init".equals(prefix)) {
            //Format: init
            command = new InitCommand(receiver);
        } else if ("undo".equals(prefix)) {
            //Format: undo
            command = new UndoCommand(commandInvoker);
        } else if ("redo".equals(prefix)) {
            //Format: redo
            command = new RedoCommand(commandInvoker);
        } else {
            System.out.println("Unknown command");
        }
        return command;
    }
}