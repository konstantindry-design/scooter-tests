package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieButton = By.id("rcc-confirm-button");

    private final By orderButtonTop = By.xpath("(//button[text()='Заказать'])[1]");
    private final By orderButtonBottom = By.xpath("(//button[text()='Заказать'])[2]");

    private final By accordionHeader = By.cssSelector(".accordion__button");
    private final By accordionContent = By.cssSelector(".accordion__panel");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public MainPage open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        return this;
    }

    public void closeCookieBanner() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
            btn.click();
        } catch (Exception e) {
            // Если баннера нет — ничего не делаем
        }
    }

    public void clickOrderButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop));
        button.click();
    }

    public void clickOrderButtonBottom() {
        WebElement button = driver.findElement(orderButtonBottom);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void clickAccordionHeader() {
        WebElement header = wait.until(ExpectedConditions.elementToBeClickable(accordionHeader));
        header.click();
    }

    public boolean isAccordionContentVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accordionContent)).isDisplayed();
    }
}


