package com.lab1.command;

import com.lab1.model.HTML;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpellCheckCommand implements Command {
    private HTML html;
    @Override
    public void execute() {
        html.spellCheck();
    }

}
