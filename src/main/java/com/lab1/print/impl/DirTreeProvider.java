package com.lab1.print.impl;


import com.lab1.print.provider.TreeContentProvider;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DirTreeProvider implements TreeContentProvider<File> {

    private File root;

    public DirTreeProvider(File root) {
        this.root = root;
    }

    @Override
    public List<File> getRoots() {
        return Arrays.asList(root.listFiles());
    }

    @Override
    public List<File> getChildren(File parent) {
        if (parent.isDirectory())
            return Arrays.asList(parent.listFiles());
        else
            return List.of();
    }

}