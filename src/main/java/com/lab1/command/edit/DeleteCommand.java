package com.lab1.command.edit;

import com.lab1.command.CanUndoCommand;
import com.lab1.model.HTML;
import com.lab1.model.HTMLTag;

public class DeleteCommand implements CanUndoCommand {

    private String id;//要删除的ID
    private HTMLTag deletedTag;//删除的HTMLTag
    private HTML html;

    public DeleteCommand(String id, HTML html){
        this.id=id;
        this.html=html;
    }
    @Override
    public void execute() {
        this.deletedTag=html.delete(id);
    }

    @Override
    public void undo() {    //TODO: 需要判断是否为根节点
        try {
            html.append(deletedTag.getName(),deletedTag.getId(),deletedTag.getParent().getId(),deletedTag.getText());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
