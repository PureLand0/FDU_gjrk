package com.lab1.command;

import com.lab1.model.HTML;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrintTreeCommand implements Command {
    private HTML html;
    @Override
    public void execute() {
        html.printTree();
    }
}