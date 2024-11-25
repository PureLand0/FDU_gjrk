package com.lab1.util;

import com.lab1.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HTMLParser {
    public static HTML parseHTML(String url) throws IOException {

        HTML html = new HTML();
        Elements elements = getHtmlElements(url);
        boolean HasSetRoot = false;
        Stack<Element> elementStack = new Stack<>();

        Element element = elements.first();
        elementStack.push(element);
        dfs(elementStack,html,HasSetRoot);

//        HTMLTag root = html.getRoot();
//        HTMLCompositeTag root1 = (HTMLCompositeTag)root;
//        List<HTMLTag> children = root1.getChildren();
//        HTMLTag head = children.get(0);
//        HTMLTag body = children.get(1);
//
//        HTMLCompositeTag arr = (HTMLCompositeTag)head;
//        List<HTMLTag> children1 = arr.getChildren();
//        HTMLTag title = children1.get(0);
//        head.setParent(root);
//        body.setParent(root);
//        title.setParent(head);


        return html;
    }

    private static HTMLTag dfs(Stack<Element> elementStack, HTML html, boolean HasSetRoot) {

        Element element = elementStack.pop();
        HTMLTag htmlTag = HTMLTagAdapter(element);

        if (element.childrenSize() != 0) { //CompositeTag
            Elements children = element.children();
            List<HTMLTag>childlist=new ArrayList<>();
            for (Element child : children) {
                elementStack.push(child);
                HTMLTag childHTMLTag=dfs(elementStack, html,true);
                childHTMLTag.attach(htmlTag);
                childlist.add(childHTMLTag);
            }
            ((HTMLCompositeTag) htmlTag).setChildren(childlist);
        }
        html.addTag(htmlTag);

        if (!HasSetRoot) {   //是根节点
            html.setRoot(htmlTag);
        }

        return htmlTag;
    }


    private static Elements getHtmlElements(String url) throws IOException {
        Document parse = Jsoup.parse(new File(url), "UTF-8");
        return parse.selectXpath(".");
    }

    private static HTMLTag HTMLTagAdapter(Element element) {
        HTMLTag htmlTag;

        String tagName = element.tagName();
        String id = getId(element);
        String text = element.ownText();

//        int childrenSize = element.childrenSize();
//        if (childrenSize == 0) {    //HTMLLeafTag
//            htmlTag = new HTMLLeafTag(tagName, id, text);
//        } else {                   //HTMLCompositeTag
//            htmlTag = new HTMLCompositeTag(tagName, id, text);
//        }
        if(TagEnum.isLeafTag(element.tagName())) {//HTMLLeafTag
            htmlTag = new HTMLLeafTag(tagName, id, text);
        }else {//HTMLCompositeTag
            htmlTag = new HTMLCompositeTag(tagName, id, text);
        }
        return htmlTag;
    }

    private static String getId(Element element) {
        String id = element.id();
        if (id == "") {
            return element.tagName();
        }
        return id;
    }
}
