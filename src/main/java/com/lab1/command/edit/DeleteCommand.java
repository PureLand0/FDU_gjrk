package com.lab1.command.edit;

import com.lab1.command.CanUndoCommand;
import com.lab1.model.HTML;
import com.lab1.model.HTMLTag;

import java.util.List;

public class DeleteCommand implements CanUndoCommand {

    private String id;//要删除的ID
    private List<HTMLTag> deletedTag;//删除的HTMLTag
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
    public void undo() {
        try {
            if(deletedTag.size()==1){//在删除的Tag之后没有兄弟Tag
                html.append(deletedTag.get(0),deletedTag.get(0).getParent());
            }
            if(deletedTag.size()==2){//在删除的Tag后还有兄弟Tag
                html.insert(deletedTag.get(0),deletedTag.get(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
