package com.lab1.command;

import com.lab1.model.HTML;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditIdCommand implements CanUndoCommand {

    private String oldId;
    private String newId;

    private HTML html;

    @Override
    public void execute() {
        html.editId(oldId, newId);
    }

    @Override
    public void undo() {
        html.editId(newId,oldId);
    }
}
