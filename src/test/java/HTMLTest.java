import com.lab1.model.HTML;
import com.lab1.util.HTMLParser;
import org.junit.Test;

import java.io.IOException;

public class HTMLTest {
    @Test
    public void testInit() throws IOException {

        HTML html= HTMLParser.parseHTML("src\\main\\resources\\TestTemplate.html");
        System.out.println("html = " + html.toString());
        html.printTree();
        html.printIndent();

    }
}
