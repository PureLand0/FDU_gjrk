package com.lab1.command;

public class RedoCommand implements Command{//恢复之前的撤销
    private CommandInvoker commandInvoker;

    public RedoCommand(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    @Override
    public void execute() {
        commandInvoker.redoLastCommand();;
    }
}
