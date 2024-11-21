package com.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 叶子标签，即里面不会再包含内容，比如 <br> <hr> <img>等
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HTMLLeafTag implements HTMLTag {
    /**
     * 标签名
     */
    private String name;
    /**
     * id
     */
    private String id;
    /**
     * 当前标签内的文本
     */
    private String text;
    /**
     * 当前节点的父节点
     */
    private HTMLTag parent;
    /**
     * 是否删除的标签值
     */


    private boolean isDeleted;

    public void setDeleted(boolean bool) {
        this.isDeleted = bool;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public HTMLLeafTag(String name,String id, String text) {
        this.name=name;
        this.id=id;
        this.text=text;

    }



    @Override
    public String print(String indent, boolean isLastChild, boolean isRoot) {
        StringBuilder result = new StringBuilder();
        result.append(indent);
        if (!isRoot & isLastChild) {
            result.append("└── ");
        } else if (!isRoot & !isLastChild) {
            result.append("├── ");
        }

        result.append(name);

        result.append("#").append(id);

        if (!text.isEmpty()) {
            if (!isLastChild) {
                result.append("\n").append(indent).append("│   └── ").append(text).append("\n");
            } else {
                result.append("\n").append(indent).append("    └── ").append(text).append("\n");
            }
        } else {
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public void attach(HTMLTag observer) {
        this.parent = observer;
    }

    @Override
    public void addUpdate(HTMLTag newTag) {
        if(this.parent instanceof HTMLCompositeTag){
            HTMLCompositeTag parentTag=(HTMLCompositeTag) this.parent;
            parentTag.addChild(parentTag.getChildren().indexOf(this), newTag);
        }
        newTag.attach(this.parent);
    }

    @Override
    public void deleteUpdate() {
        if(this.parent instanceof HTMLCompositeTag){
            HTMLCompositeTag parentTag=(HTMLCompositeTag) this.parent;
            parentTag.removeChild(this);
        }

    }

    @Override
    public void deleteUndoUpdate() {
        if(this.parent instanceof HTMLCompositeTag){
            HTMLCompositeTag parentTag=(HTMLCompositeTag) this.parent;
            parentTag.addChild(this);
        }


    }
}
