package AcmeStoreUI.tests;

import AcmeStoreUI.pages.AcmeHomePage;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static AcmeStoreUI.pages.AcmeHomePage.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class HomePageTests extends BaseTest {

    public static Logger logger = LoggerFactory.getLogger(HomePageTests.class);


    @BeforeMethod
    public void openMainPage() {
        acmeHomePage.openMainPage();
    }

    AcmeHomePage acmeHomePage = new AcmeHomePage();

    @Test(description = "тест проверяет ссылку на главной странице Home")
    public void HomePageLinkTest() {
        assertLink(HOME_PAGE_LINK);
        logger.info(acmeHomePage.getMainPageLink());
    }

    @Test(description = "тест нажимает кнопку HomePage и проверяет открывшуюся ссылку")
    public void HomePageBottomTest() {
        acmeHomePage.homePageBottomClick();
        assertLink(acmeHomePage.getMainPageLink());
    }

    @Test(description = "тест нажимает кнопку RubberDuck и проверяет открывшуюся ссылку")
    public void RubberDuckBottomTest() {
        acmeHomePage.rubberDuckBottomClick();
        assertLink(RUBBER_DUCKS_LINK);
        acmeHomePage.checkRubberDuckElementList.shouldHave(text(" » Rubber Ducks"));
    }

    @Test(description = "тест нажимает кнопку Subcategory и проверяет открывшуюся ссылку")
    public void SubcategoryBottomTest() {
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(acmeHomePage.selectRubberDuckBottom).perform();
        acmeHomePage.subcategoryBottomClick();
        assertLink(SUBCATEGORY_LINK);
        acmeHomePage.checkSubcategoryElementList.shouldHave(text(" » Subcategory"));
    }
}