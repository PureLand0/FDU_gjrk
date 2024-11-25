package com.lab1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * HTML标签的接口
 */
public interface HTMLTag {

    public String getName();

    public void setName(String name);

    public String getId();

    public void setId(String id);

    public String getText();

    public void setText(String newText);

    public HTMLTag getParent();

    public void setParent(HTMLTag newTag);

    public void setDeleted(boolean bool);

    public boolean isDeleted();

    /**
     * 打印当前节点的树结构
     * @param indent 这个节点前面的前缀
     * @param isLastChild 是不是当前范围最后一个结点
     * @param isRoot 是不是根结点
     * @return
     */
    public String print(String indent, boolean isLastChild, boolean isRoot);

    /**
     * 添加观察者
     * @param observer 观察者
     */
    public void attach(HTMLTag observer);

    /**
     * 新节点要插在当前结点的位置上
     * @param newTag
     */
    public void addUpdate(HTMLTag newTag);

    /**
     * 要删除当前结点
     */
    public void deleteUpdate();

    public int getChildrenSize();

}
