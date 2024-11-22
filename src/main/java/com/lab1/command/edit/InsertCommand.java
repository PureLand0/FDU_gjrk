package com.lab1.command.edit;

import com.lab1.command.CanUndoCommand;
import com.lab1.model.HTML;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsertCommand implements CanUndoCommand {//将新元素加入到id对应元素之前

    private String name;
    private String id;
    private String insertLocationId;
    private String text;
    private HTML html;


    @Override
    public void execute() {
        //先找到insertLocationId对应的tag
        html.insert(name, id, insertLocationId, text);
    }

    @Override
    public void undo() {
        html.delete(id);
    }
}
