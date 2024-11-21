package com.lab1.command;

import java.util.Stack;

public class CommandInvoker {
    private final Stack<CanUndoCommand> commandStack = new Stack<>();//装已经执行的命名
    private final Stack<CanUndoCommand> undoneCommands = new Stack<>();//装被撤销的命令


    public void storeAndExecute(Command command) {
        command.execute();
        if (command instanceof CanUndoCommand) {
            commandStack.push((CanUndoCommand) command);
            undoneCommands.clear();
        }
    }

    public void undoLastCommand() {
        if (!commandStack.isEmpty()) {
            CanUndoCommand command = commandStack.pop();
            command.undo();
            undoneCommands.push(command);
        }
    }

    public void redoLastCommand() {
        if (!undoneCommands.isEmpty()) {
            CanUndoCommand command = undoneCommands.pop();
            command.execute();
            commandStack.push(command);
        }
    }
}

