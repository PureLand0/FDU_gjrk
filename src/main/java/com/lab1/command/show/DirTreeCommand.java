package com.lab1.command.show;

import com.lab1.command.Command;
import com.lab1.model.HTML;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DirTreeCommand implements Command {
    private HTML html;
    @Override
    public void execute() {
        html.dirTree();
    }
}