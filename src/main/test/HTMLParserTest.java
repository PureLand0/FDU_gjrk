import com.lab1.model.HTML;
import com.lab1.utils.HTMLParser;
import org.junit.Test;

import java.io.IOException;

public class HTMLParserTest {

    @Test
    public void testHTMLParser() throws IOException {
        HTML html= HTMLParser.parseHTML("src\\main\\resources\\HTMLTemplate.html");
        System.out.println("html.toString() = " + html.toString());
    }

}
