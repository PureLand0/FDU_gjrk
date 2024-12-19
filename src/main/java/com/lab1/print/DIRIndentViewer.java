package com.lab1.print;

import com.lab1.print.provider.NameProvider;
import com.lab1.print.provider.TreeContentProvider;

import java.util.List;


public class DIRIndentViewer<T> implements Viewer{

    private TreeContentProvider<T> contentProvider;
    private NameProvider<T> nameProvider;
    StringBuilder sb = new StringBuilder();

    public DIRIndentViewer(TreeContentProvider<T> contentProvider, NameProvider<T> nameProvider) {
        this.contentProvider = contentProvider;
        this.nameProvider = nameProvider;
    }

    private void printIdent(int ident) {
        for (int i = 0; i < ident; i++) {
//            System.out.print("\t");
            sb.append("\t");
        }
    }

    void show(T node, int ident) {
        printIdent(ident);
//        System.out.println(nameProvider.getName(node));
        sb.append(nameProvider.getName(node) + "\n");
        List<T> roots = contentProvider.getChildren(node);
        for (T root : roots) {
            show(root, ident + 1);
        }
    }

    // 显示使用空格缩进的树形结构
    public StringBuilder show() {
        List<T> roots = contentProvider.getRoots();
        for (T root : roots) {
            show(root, 0);
        }
        return sb;
    }
}
