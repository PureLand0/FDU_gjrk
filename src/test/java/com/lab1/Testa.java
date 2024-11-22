package com.lab1;

import com.lab1.model.HTML;
import com.lab1.model.HTMLCompositeTag;
import com.lab1.model.HTMLLeafTag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Testa {

    @Test
    public void test1() {
        HTMLCompositeTag html = new HTMLCompositeTag("html", "html", "", null, null, false);
        HTMLCompositeTag head = new HTMLCompositeTag("head", "head", "", null, null, false);
        HTMLCompositeTag body = new HTMLCompositeTag("body", "body", "", null, null, false);

        HTMLLeafTag title = new HTMLLeafTag("title", "title", "my webpage", null, false);
        HTMLCompositeTag h1 = new HTMLCompositeTag("h1", "h1", "welcom", null, null, false);
        HTMLCompositeTag list = new HTMLCompositeTag("ul", "list", "list!!!", null, null, false);
        HTMLLeafTag i1 = new HTMLLeafTag("li", "i1", "i1", null, false);
        HTMLLeafTag i2 = new HTMLLeafTag("li", "i2", "i2", null, false);
        HTMLLeafTag i3 = new HTMLLeafTag("li", "i3", "i3", null, false);


        html.addChild(head);
        html.addChild(body);

        head.addChild(title);

        body.addChild(h1);
        body.addChild(list);

        list.addChild(i1);
        list.addChild(i2);
        list.addChild(i3);

        HTML html1 = new HTML(html);


        html1.printTree();




    }

    @Test
    public void checkspell() throws IOException {
        new HTML().spellCheck();
    }
}
