package com.lab1.command;

public class UndoCommand implements Command{//撤销命令
    private CommandInvoker commandInvoker;

    public UndoCommand(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    @Override
    public void execute() {
        commandInvoker.undoLastCommand();
    }
}
