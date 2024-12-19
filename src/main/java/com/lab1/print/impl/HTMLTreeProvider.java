package com.lab1.print.impl;


import com.lab1.model.HTMLCompositeTag;
import com.lab1.model.HTMLTag;
import com.lab1.print.provider.TreeContentProvider;
import java.util.List;

public class HTMLTreeProvider implements TreeContentProvider<HTMLTag> {

    private HTMLTag root;

    public HTMLTreeProvider(HTMLTag root) {
        this.root = root;
    }

    @Override
    public List<HTMLTag> getRoots() {
        return (List<HTMLTag>) ((HTMLCompositeTag) root).getChildren();
    }

    @Override
    public List<HTMLTag> getChildren(HTMLTag parent) {
        if (parent instanceof HTMLCompositeTag)
            return (List<HTMLTag>) ((HTMLCompositeTag) parent).getChildren();
        else
            return List.of();
    }
}