package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionsTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    // test commit
    @Test
    public void checkAccordionText() {
        mainPage.open();
        mainPage.closeCookieBanner();
        mainPage.clickAccordionHeader();

        assertThat(mainPage.isAccordionContentVisible()).isTrue();
    }
}


