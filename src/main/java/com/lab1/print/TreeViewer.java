package com.lab1.print;

import com.lab1.model.HTMLTag;

public class TreeViewer implements Viewer{
    private HTMLTag root;
    private StringBuilder sb;

    public TreeViewer(HTMLTag root) {
        this.root = root;
    }

    @Override
    public StringBuilder show() {
        sb = new StringBuilder(root.print("",false,true));
        return sb;
    }
}
