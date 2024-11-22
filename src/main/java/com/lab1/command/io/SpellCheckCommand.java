package com.lab1.command.io;

import com.lab1.command.Command;
import com.lab1.model.HTML;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;

@Data
@AllArgsConstructor
public class SpellCheckCommand implements Command {
    private HTML html;
    @Override
    public void execute() {
        try {
            html.spellCheck();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
