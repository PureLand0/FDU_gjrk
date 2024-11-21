package com.lab1.command;

import com.lab1.model.HTML;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrintIndentCommand implements Command {//按缩进格式显示
    private int indent;
    private HTML html;

    @Override
    public void execute() {
        html.printIndent(indent);
    }

}
