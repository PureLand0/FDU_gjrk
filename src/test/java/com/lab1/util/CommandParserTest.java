package com.lab1.util;

import com.lab1.command.Command;
import com.lab1.command.CommandInvoker;
import com.lab1.command.RedoCommand;
import com.lab1.command.UndoCommand;
import com.lab1.command.edit.*;
import com.lab1.command.show.PrintIndentCommand;
import com.lab1.command.show.PrintTreeCommand;
import com.lab1.command.show.io.InitCommand;
import com.lab1.command.show.io.ReadCommand;
import com.lab1.command.show.io.SaveCommand;
import com.lab1.command.show.io.SpellCheckCommand;
import com.lab1.model.HTML;
import com.lab1.print.Viewer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class CommandParserTest {

    private String appendCommand="append tagName idValue parentElement [textContent]";
    private String deleteCommand="delete element";
    private String editIdCommand="edit-id oldId newId";
    private String editTextCommand="edit-text element [newTextContent]";
    private String insertCommand="insert tagName idValue insertLocation [textContent]";
    private String initCommand="init";
    private String readCommand="read filepath";
    private String saveCommand="save filepath";
    private String spellCheckCommand="spell-check";
    private String printIndentCommand="print-indent";
    private String printTreeCommand="print-tree";
    private String undoCommand="undo";
    private String redoCommand="redo";
    private String errorCommand="ERROR COMMAND";

    private CommandInvoker commandInvoker = new CommandInvoker();
    
    private HTML html = new HTML();

    @Test
    public void appendCommandTest(){
        Command command=CommandParser.parse(appendCommand,commandInvoker,html);
        assert(command instanceof AppendCommand);
    }
    @Test
    public void deleteCommandTest(){
        Command command=CommandParser.parse(deleteCommand,commandInvoker,html);
        assert(command instanceof DeleteCommand);
    }
    @Test
    public void editIdCommandTest(){
        Command command=CommandParser.parse(editIdCommand,commandInvoker,html);
        assert(command instanceof EditIdCommand);
    }
    @Test
    public void editTextCommandTest(){
        Command command=CommandParser.parse(editTextCommand,commandInvoker,html);
        assert(command instanceof EditTextCommand);
    }
    @Test
    public void insertCommandTest(){
        Command command=CommandParser.parse(insertCommand,commandInvoker,html);
        assert(command instanceof InsertCommand);
    }
    @Test
    public void saveCommandTest(){
        Command command=CommandParser.parse(saveCommand,commandInvoker,html);
        assert(command instanceof SaveCommand);
    }
    @Test
    public void readCommandTest(){
        Command command=CommandParser.parse(readCommand,commandInvoker,html);
        assert(command instanceof ReadCommand);
    }
    @Test
    public void spellCheckCommandTest(){
        Command command=CommandParser.parse(spellCheckCommand,commandInvoker,html);
        assert(command instanceof SpellCheckCommand);
    }
    @Test
    public void printIndentCommandTest(){
        Command command=CommandParser.parse(printIndentCommand,commandInvoker,html);
        assert(command instanceof PrintIndentCommand);
    }
    @Test
    public void printTreeCommandTest(){
        Command command=CommandParser.parse(printTreeCommand,commandInvoker,html);
        assert(command instanceof PrintTreeCommand);
    }
    @Test
    public void undoCommandTest(){
        Command command=CommandParser.parse(undoCommand,commandInvoker,html);
        assert(command instanceof UndoCommand);
    }
    @Test
    public void redoCommandTest(){
        Command command=CommandParser.parse(redoCommand,commandInvoker,html);
        assert(command instanceof RedoCommand);
    }
    @Test
    public void errorCommandTest(){
        Command command=CommandParser.parse(errorCommand,commandInvoker,html);
        assert(command==null);
    }
    
}
