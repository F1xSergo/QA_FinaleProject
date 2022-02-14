package Ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

abstract public class MainTest {
    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }
}
