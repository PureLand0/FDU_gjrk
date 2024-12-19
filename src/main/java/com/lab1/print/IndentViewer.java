package com.lab1.print;

import com.lab1.model.HTMLCompositeTag;
import com.lab1.model.HTMLLeafTag;
import com.lab1.model.HTMLTag;

import java.util.List;

public class IndentViewer implements Viewer{
    private HTMLTag root;
    private int indent;
    private StringBuilder sb;

    public IndentViewer(HTMLTag root) {
        this.root = root;
        this.indent = 2;
        sb = new StringBuilder();
    }

    public IndentViewer(HTMLTag root, int indent) {
        this.root = root;
        this.indent = indent;
        sb = new StringBuilder();
    }

    private StringBuilder printIdent(int indent) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            builder.append("\t");
        }
        return builder;
    }

    void show(HTMLTag node, int indent) {
        sb.append(printIdent(indent));
        sb.append("<").append(node.getName()).append(" id=\"").append(node.getId()).append("\"").append(">");
        if (node instanceof HTMLLeafTag || (node instanceof HTMLCompositeTag && ((HTMLCompositeTag) node).getChildren().size() == 0)) {
            sb.append(node.getText()).append("<").append("/").append(node.getName()).append(">").append("\n");
        } else {
            sb.append("\n");
            if(node.getText().equals("")){
            }else {
                sb.append(printIdent(indent+1)).append(node.getText()).append("\n");
            }
            HTMLCompositeTag node1 = (HTMLCompositeTag) node;
            List<HTMLTag> nodes = node1.getChildren();
            if (nodes != null) {
                for (HTMLTag nodetemp : nodes) {
                    show(nodetemp, indent + 1);
                }
            }
            sb.append(printIdent(indent));
            sb.append("<").append("/").append(node.getName()).append(">").append("\n");
        }
    }

    // 显示使用空格缩进的树形结构
    @Override
    public StringBuilder show() {
        HTMLCompositeTag htmlCompositeTag = (HTMLCompositeTag) root;
        sb.append("<").append(root.getName()).append(" id=\"").append(root.getId()).append("\"").append(">").append("\n");
        List<HTMLTag> nodes = htmlCompositeTag.getChildren();
        for (HTMLTag node : nodes) {
            show(node, 1);
        }
        sb.append("<").append("/").append(root.getName()).append(">");
        return sb;
    }
}
