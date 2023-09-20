package AcmeStoreApp.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

abstract public class BaseTest {                                                                                        //класс с общими методами от которого будем наследоваться другими тестовыми классами

    public void setUp() {                                                                                               //метод настройки и инициализации браузера
        WebDriverManager.chromedriver().setup();                                                                        //скачивает драйвер, прописывает путь, и осн. настройки.
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        Configuration.browser = "chrome";
        Configuration.browserVersion = "116";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;                                                                                 //будем ли видеть браузер при выполнении тестов
    }

    @BeforeTest
    public void init() {
        setUp();
    }

    @AfterTest
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}