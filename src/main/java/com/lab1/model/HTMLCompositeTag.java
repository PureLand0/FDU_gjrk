package com.lab1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 枝干节点，里面会再包含内容，比如 <div> <head> <html>等
 */
@Data
@NoArgsConstructor

public class HTMLCompositeTag implements HTMLTag {

    private String name;

    private String id;

    private String text;

    private HTMLTag parent;


    //private List<String> childrenId = new ArrayList<>();

    private List<HTMLTag> children = new ArrayList<>();

    private boolean isDeleted;

    public HTMLCompositeTag(String name, String id, String text, HTMLTag parent, List<HTMLTag> children, boolean isDeleted) {
        this.name = name;
        this.id = id;
        this.text = text;
        this.parent = parent;
        if(children == null) {
            this.children = new ArrayList<>();
        }

        this.isDeleted = isDeleted;
    }

    public void setDeleted(boolean bool) {
        this.isDeleted = bool;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void addChild(HTMLTag child) {
        if(this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

    public void addChild(int index, HTMLTag child) {
        this.children.add(index, child);
    }

    public void removeChild(HTMLTag child){
        this.children.remove(child);
    }
    public int getChildrenSize(){
        return children.size();
    }

    public HTMLCompositeTag(String name,String id, String text) {
        this.name=name;
        this.id=id;
        this.text=text;
    }
    public HTMLTag getNextChild(HTMLTag htmlTag){
        for (int i = 0; i < children.size()-1; i++) {
            if(children.get(i).equals(htmlTag)){
                return children.get(i+1);//返回下一个孩子
            }
        }
        return null;//代表没有下一个孩子
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

        //孩子的前缀
        String indentToChildren = "";
        if (!isRoot) {
            if (!isLastChild) {
                indentToChildren = indent + "│   ";
            } else {
                indentToChildren = indent + "    ";
            }
        } else {
            indentToChildren = indent;
        }

        //处理文字部分
        if (!text.isEmpty()) {
            //如果有孩子
            if (!children.isEmpty()) {
                result.append("\n").append(indentToChildren).append("├── ").append(text).append("\n");
            } else {
                //无孩子
                result.append("\n").append(indentToChildren).append("└── ").append(text).append("\n");
            }
        } else {
            result.append("\n");
        }

        //处理孩子部分
        for (int i = 0; i < children.size(); i++) {
            HTMLTag child = children.get(i);
            if (i == children.size() - 1) {
                //如果是最后一个
                result.append(child.print(indentToChildren, true, false));
            } else {
                result.append(child.print(indentToChildren, false, false));
            }
        }
        return result.toString();
    }

    @Override
    public void attach(HTMLTag observer) {
        this.parent=observer;
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
        //父节点删除
        this.setDeleted(true);
        if(this.parent instanceof HTMLCompositeTag){
            HTMLCompositeTag parentTag = (HTMLCompositeTag) this.parent;
            parentTag.removeChild(this);
            System.out.println("parentTag = " + parentTag);
        }
    }

    @Override
    public String toString() {

        if(parent==null){
            return "HTMLCompositeTag{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", text='" + text + '\'' +
                    ", parentID=" + " " +
                    ", childrenSize=" + children.size() +
                    ", isDeleted=" + isDeleted +
                    '}';
        }
        else {
            return "HTMLCompositeTag{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", text='" + text + '\'' +
                    ", parentID=" + parent.getId() +
                    ", childrenSize=" + children.size() +
                    ", isDeleted=" + isDeleted +
                    '}';
        }
    }


}
