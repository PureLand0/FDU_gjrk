package com.lab1.command.edit;

import com.lab1.command.CanUndoCommand;
import com.lab1.model.HTML;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppendCommand implements CanUndoCommand { //将新元素插入到parentId对应元素的内部

    private String parentId;
    private String name;
    private String id;
    private String text;
    private HTML html;

    @Override
    public void execute() {
        try {
            html.append(name, id, parentId, text);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void undo() {
        html.delete(id);
    }
}
