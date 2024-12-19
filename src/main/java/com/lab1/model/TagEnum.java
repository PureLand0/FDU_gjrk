package com.lab1.model;

import java.util.ArrayList;
import java.util.List;

public class TagEnum {
    /**
     * <img>：用于嵌入图像。
     * <br>：用于插入换行。
     * <hr>：用于插入水平线。
     * <input>：用于创建输入字段。
     * <link>：用于链接外部资源，如CSS文件。
     * <meta>：用于提供关于HTML文档的元数据。
     * <area>：用于定义图像映射中的区域。
     * <base>：指定文档中所有相对URL的基准URL。
     * <col>：用于定义表格列的属性。
     * <command>：表示用户可以执行的命令。
     * <embed>：用于嵌入外部应用程序或交互式内容。
     * <keygen>：用于为表单提供密钥对生成器（HTML5中已废弃）。
     * <param>：用于定义对象的参数。
     * <source>：用于指定媒体资源。
     * <track>：用于为媒体元素指定字幕文件或其他文本轨道。
     * <wbr>：用于插入可能的换行符。
     */
    /**
     * <div>：通用容器，用于对网页内容进行布局和分组。
     * <span>：内联容器，用于对文本内容进行分组和样式化。
     * <p>：段落标签，用于包含文本内容。
     * <h1> 到 <h6>：标题标签，用于包含标题文本。
     * <ul>：无序列表，可以包含多个 <li>（列表项）元素。
     * <ol>：有序列表，可以包含多个 <li> 元素。
     * <li>：列表项，可以包含其他元素。
     * <table>：表格标签，可以包含 <thead>、<tbody>、<tfoot>、<tr>、<th> 和 <td> 等元素。
     * <tr>：表格行，可以包含多个 <td>（单元格）或 <th>（表头单元格）元素。
     * <td> 和 <th>：表格单元格，可以包含其他元素。
     * <form>：表单标签，可以包含各种表单元素，如 <input>、<select>、<textarea> 等。
     * <section>：定义文档中的一个区段，可以包含其他元素。
     * <article>：定义独立的自包含内容，可以包含其他元素。
     * <header>：定义文档或区段的页眉，可以包含其他元素。
     * <footer>：定义文档或区段的页脚，可以包含其他元素。
     * <aside>：定义与页面主要内容稍微相关的部分，可以包含其他元素。
     * <nav>：定义导航链接的部分，可以包含其他元素。
     * <main>：定义文档的主要内容，可以包含其他元素。
     * <figure>：定义自包含的内容，通常用于图片和图表，可以包含 <figcaption> 等元素。
     * <blockquote>：定义引用或引述的文本，可以包含其他元素。
     * <html></>
     * <head></>
     * <title></>
     * <>body</>
     */
    private static List<String> leafList = new ArrayList<>();
    private static List<String> compositeList = new ArrayList<>();
    static {
        leafList.add("img");
        leafList.add("br");
        leafList.add("hr");
        leafList.add("input");
        leafList.add("link");
        leafList.add("meta");
        leafList.add("area");
        leafList.add("base");
        leafList.add("col");
        leafList.add("command");
        leafList.add("embed");
        leafList.add("keygen");
        leafList.add("param");
        leafList.add("source");
        leafList.add("track");
        leafList.add("wbr");
        leafList.add("title");

        compositeList.add("div");
        compositeList.add("span");
        compositeList.add("p");
        compositeList.add("h1");
        compositeList.add("h2");
        compositeList.add("h3");
        compositeList.add("h4");
        compositeList.add("h5");
        compositeList.add("h6");
        compositeList.add("ul");
        compositeList.add("ol");
        compositeList.add("li");
        compositeList.add("table");
        compositeList.add("tr");
        compositeList.add("td");
        compositeList.add("th");
        compositeList.add("form");
        compositeList.add("section");
        compositeList.add("article");
        compositeList.add("html");
        compositeList.add("head");
        compositeList.add("body");
        compositeList.add("headre");
        compositeList.add("footer");
        compositeList.add("aside");
        compositeList.add("nav");
        compositeList.add("main");
        compositeList.add("figure");
        compositeList.add("blockquote");
    }
    public static boolean isLeafTag(String s) {
        if(leafList.contains(s))return true;
        return false;
    }
    public static boolean isCompositeTag(String s) {
        if(compositeList.contains(s))return true;
        return false;
    }
}
