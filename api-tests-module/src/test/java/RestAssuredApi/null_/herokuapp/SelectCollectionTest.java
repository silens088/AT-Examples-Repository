package RestAssuredApi.null_.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SelectCollectionTest {

    private final static String URL = "https://the-internet.herokuapp.com/";
    private final static String DragDropElementLink = "div#content a[href*=\"/drag_and_drop\"]";

    @Test
    public void openSiteAndParseLinks() {
        Selenide.open(URL);
        ElementsCollection collect = $$("div#content a[href]");
        List<String> listWithLinks = new ArrayList<>();
        for (SelenideElement element : collect) {
            listWithLinks.add(element.getAttribute("href"));
        }

        $(DragDropElementLink).shouldHave(text("Drag and Drop")).click();

        String dragDropLink = listWithLinks.get(9);
        String currentLink = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertEquals(currentLink, dragDropLink);
    }
}