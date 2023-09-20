package DemoqaApp.tests;

import DemoqaApp.pages.RegistrationPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Locale;

abstract public class BaseTest {                                                                                        //класс с общими методами от которого будем наследоваться другими тестовыми классами

    public static Faker faker = new Faker(new Locale("ru")); //выставили локализацию фейкера
    public static String randomName = faker.name().firstName();
    public static String randomTelephone = faker.number().digits(10);
    public static String randomEmail = RandomUtilsForTests.getRandomEmail();


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