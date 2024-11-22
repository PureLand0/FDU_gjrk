package com.lab1.command;

import com.lab1.model.HTML;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditTextCommand implements CanUndoCommand {

    private String id;
    private String newText;
    private String oldText=null;
    private HTML html;

    public EditTextCommand(String elementId, String newTextContent, HTML html) {
        this.id = elementId;
        this.newText = newTextContent;
        this.html = html;
    }

    @Override
    public void execute() {
        this.oldText= html.editText(id,newText);
    }

    @Override
    public void undo() {
        this.oldText= html.editText(id,oldText);
    }
}
