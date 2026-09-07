package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QuestionsTest {

    private WebDriver driver;
    private MainPage mainPage;

    private final int index;
    private final String expectedAnswer;

    public QuestionsTest(int index, String expectedAnswer) {
        this.index = index;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, "Ответ на первый вопрос"},
                {1, "Ответ на второй вопрос"},
                {2, "Ответ на третий вопрос"},
                {3, "Ответ на четвёртый вопрос"},
                {4, "Ответ на пятый вопрос"}
        });
    }

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

    @Test
    public void checkAccordionAnswer() {
        mainPage.open();
        mainPage.closeCookieBanner();
        mainPage.clickAccordionHeaderByIndex(index);
        assertThat(mainPage.getAccordionAnswerByIndex(index)).isEqualTo(expectedAnswer);
    }
}



