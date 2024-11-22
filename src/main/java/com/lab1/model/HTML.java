package com.lab1.model;

import com.lab1.print.SimpleTreeViewer;
import lombok.Data;
import org.languagetool.JLanguageTool;
import org.languagetool.Languages;
import org.languagetool.markup.AnnotatedText;
import org.languagetool.markup.AnnotatedTextBuilder;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 是HTML文档类
 */
@Data
public class HTML {
    //根节点
    private HTMLTag root;
    // Map存储id-Tag 用于查找
    private Map<String, HTMLTag> map;


    public HTML(HTMLTag root) {
        this.root = root;
        map = new HashMap<>();
    }

    public HTML() {

    }


    /**
     * 1. insert 在某元素之前插入新元素
     *
     * @param tagName        为新元素的元素标签
     * @param idValue        为新元素的 id，注意 id 不能与其他元素重复
     * @param insertLocation 为插入位置元素的 id，新元素将插入到该元素之前
     * @param textContent    为可选参数，表示新元素中的文本内容
     */
    public void insert(String tagName, String idValue, String insertLocation, String textContent) {
        //检验
        if (!TagEnum.isLeafTag(tagName) && !TagEnum.isCompositeTag(tagName)) {
            throw new IllegalArgumentException("标签名非法");
        }
        if (map.containsKey(idValue)) {
            throw new IllegalArgumentException("ID重复");
        }
        //找到插入位置的tag
        HTMLTag insertLocationTag = findTagById(insertLocation);
        if (insertLocationTag != null) {
            HTMLTag newTag = null;
            if (TagEnum.isLeafTag(tagName)) {
                //新加的是叶子
                newTag = new HTMLLeafTag(tagName, idValue, textContent, null, false);
            } else {
                //新加的是枝干
                newTag = new HTMLCompositeTag(tagName, idValue, textContent, null, null, null, false);
            }
            //真正的插入
            insertLocationTag.addUpdate(newTag);
            map.put(idValue, newTag);
        } else {
            System.out.println("找不到你所要插入的位置");
        }
    }

    /**
     * 2. append 在某元素内插入新元素
     *
     * @param tagName       为新元素的元素标签
     * @param idValue       为新元素的 id，注意 id 不能与其他元素重复
     * @param parentElement 为目标父元素的 id，新元素将被插入到该元素内部，并且成为该元素的最后一个子元素
     * @param textContent   为可选参数，表示新元素中的文本内容
     */
    public void append(String tagName, String idValue, String parentElement, String textContent) {
        //检验
        if (!TagEnum.isLeafTag(tagName) && !TagEnum.isCompositeTag(tagName)) {
            throw new IllegalArgumentException("标签名非法");
        }
        if (map.containsKey(idValue)) {
            throw new IllegalArgumentException("ID重复");
        }
        //
        HTMLTag parentTag = findTagById(parentElement);
        if (parentTag != null) {
            if (!TagEnum.isCompositeTag(parentTag.getName())) {
                throw new IllegalArgumentException("目标父元素内不可加入元素");
            }
            HTMLTag newTag = null;
            if (TagEnum.isLeafTag(tagName)) {
                //leaf
                newTag = new HTMLLeafTag(tagName, idValue, textContent, parentTag, false);
            } else {
                //composite
                newTag = new HTMLCompositeTag(tagName, idValue, textContent, parentTag, null, null, false);
            }
            map.put(idValue, newTag);
            HTMLCompositeTag parentCompositeTag = (HTMLCompositeTag) parentTag;
            parentCompositeTag.addChild(newTag);
        } else {
            System.out.println("找不到你所要插入的位置");
        }
    }


    /**
     * 3. edit-id 编辑某元素的 id
     *
     * @param oldId 为现有元素的 id
     * @param newId 为新 id，注意 id 不能与其他元素重复
     */
    public void editId(String oldId, String newId) {
        if (map.containsKey(newId)) {
            throw new IllegalArgumentException("ID重复");
        }
        if (!map.containsKey(oldId)) {
            throw new IllegalArgumentException("没有你要找的元素");
        }

        //根据oldId找到对应的tag
        HTMLTag originalTag = findTagById(oldId);
        originalTag.setId(newId);
        //更新map
        map.remove(oldId);
        map.put(newId, originalTag);

    }


    /**
     * 4. edit-text 编辑某元素内部的文本
     *
     * @param element        为要编辑元素的 id
     * @param newTextContent 为新的文本内容，可以为空，表示清空该元素的文本内容
     * @return 返回原始文本内容
     */
    public String editText(String element, String newTextContent) {
        if (!map.containsKey(element)) {
            throw new IllegalArgumentException("没有你要找的元素");
        }
        HTMLTag originalTag = findTagById(element);

        String oldText = originalTag.getText();
        originalTag.setText(newTextContent);
        return oldText;
    }

    /**
     * 5. delete 删除某元素
     * TODO 1
     *
     * @param element 为要删除元素的 id
     */
    public HTMLTag delete(String element) {
        if (!map.containsKey(element)) {
            throw new IllegalArgumentException("没有你要找的元素");
        }
        HTMLTag originalTag = findTagById(element);
        map.remove(element);
        originalTag.setDeleted(true);
        //更新状态，通知树中其他节点
        originalTag.deleteUpdate();
        return originalTag;
    }

    /**
     * 6. print-indent 按缩进格式显示
     *
     * @param indent 为可选参数，表示每级缩进的空格数，默认为 2。当提供 indent 时，使用指定的空格数进行缩进显示
     */
    public void printIndent(int indent) {
        if (root == null) {
            throw new RuntimeException("当前html为空");
        }
        SimpleTreeViewer simpleTreeViewer = new SimpleTreeViewer(root, indent);
        System.out.println(new String(simpleTreeViewer.show()));
    }

    /**
     * 7.print-tree 按树型格式显示
     */
    public void printTree() {
        if (root == null) {
            throw new RuntimeException("当前html为空");
        }
        printer = new TreePrinter();
        if (root == null) {
            System.out.println("EMPTY HTML,PLEASE READ OR INIT");
        } else {
            System.out.println(printer.format(root));
        }
    }


    /**
     * 根据id查找标签
     *
     * @param id
     * @return
     */
    public HTMLTag findTagById(String id) {
        return map.get(id);
    }

    /**
     * 添加标签并检查 id 是否重复
     *
     * @param tag
     * @throws IllegalArgumentException
     */
    public void addTag(HTMLTag tag) throws IllegalArgumentException {
        if (map.containsKey(tag.getId())) {
            throw new IllegalArgumentException("Duplicate id: " + tag.getId());
        }
        map.put(tag.getId(), tag);  // 将标签添加到 map 中
    }

    /**
     * 8. spell-check 显示拼写检查结果
     * - 调用拼写检查服务，显示拼写检查结果。检查结果的格式自定，合理即可。
     * - 需要能够检查 HTML 中的所有 text 内容。
     */
    public void spellCheck() throws IOException {
        // 假设这是你的HTML内容
        String htmlContent = "<html>\n" +
                "  <head>\n" +
                "    <title>My Webpage</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h1 id=\"title\">Welcome to my webpage</h1>\n" +
                "    <p id=\"description\">This is a paragraph.</p >\n" +
                "    <ul id=\"list\">\n" +
                "      <li id=\"item1\">Item 1</li>\n" +
                "      <li id=\"item2\">Item 2</li>\n" +
                "      <li id=\"item3\">Item 3</li>\n" +
                "    </ul>\n" +
                "    <div id=\"footer\">\n" +
                "      this is a text contect in div\n" +
                "      <p id=\"last-updated\">Last updated: 2024-01-01</p >\n" +
                "      <p id=\"copyright\">Copyright © 2021 MyWebpage.com</p >\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>";
        // 正则表达式匹配纯文本内容
        String text = htmlContent.replaceAll("<[^>]+>", "").replaceAll("(\\r?\\n\\s*){2,}", " ").trim();
        JLanguageTool langTool = new JLanguageTool(Languages.getLanguageForShortCode("en-GB"));
        // 检查文本
        List<RuleMatch> matches = langTool.check(text);
        // 输出检查结果
        for (RuleMatch match : matches) {
            System.out.println("Possible spell error: " +
                    text.substring(match.getFromPos(), match.getToPos()) + "->" +
                    match.getSuggestedReplacements());
        }
    }

}
