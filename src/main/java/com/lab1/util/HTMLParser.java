package com.lab1.util;
import com.lab1.model.HTML;
import com.lab1.model.HTMLCompositeTag;
import com.lab1.model.HTMLLeafTag;
import com.lab1.model.HTMLTag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTMLParser {
    public static HTML parseHTML(String url) throws IOException {

        HTML html=new HTML();
        Elements elements =getHtmlElements(url);
        boolean HasSetRoot=false;

        while(elements.size()!=0){//层序遍历

            Element element=elements.first();
            HTMLTag htmlTag=HTMLTagAdapter(element);
            if(!HasSetRoot){   //设置html根节点
                html.setRoot(htmlTag);
                HasSetRoot=true;
            }
            else {             //设置父节点
                htmlTag.attach(html.findTagById(getId(element.parent())));
            }

            //设置孩子节点
            List<String> childrenId=new ArrayList<>();
            if(element.childrenSize()!=0){ //CompositeTag
                Elements children = element.children();
                for (Element child : children) {
                    elements.add(child);

                    childrenId.add(getId(child));
                    ((HTMLCompositeTag) htmlTag).setChildrenId(childrenId);
                }
            }
            html.addTag(htmlTag);

            elements.remove(0);
        }
        return html;
    }
    private static Elements getHtmlElements(String url) throws IOException{
        Document parse = Jsoup.parse(new File(url), "UTF-8");
        return parse.selectXpath(".");
    }
    private static HTMLTag HTMLTagAdapter(Element element){
        HTMLTag htmlTag;

        String tagName = element.tagName();
        String id = getId(element);
        String text=element.ownText();

        int childrenSize = element.childrenSize();
        if(childrenSize==0){    //HTMLLeafTag
            htmlTag=new HTMLLeafTag(tagName,id,text);
        }
        else{                   //HTMLCompositeTag
            htmlTag=new HTMLCompositeTag(tagName,id,text);
        }
        return htmlTag;
    }

    private static String getId(Element element){
        String id=element.id();
        if(id==""){
            return element.tagName();
        }
        return id;
    }
}
