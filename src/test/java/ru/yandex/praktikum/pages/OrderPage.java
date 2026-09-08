package ru.yandex.praktikum.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By metroInputLocator = By.cssSelector("input.select-search__input");
    private final By metroDropdownContainer = By.cssSelector("div.select-search__select");
    private final By metroOptionButtons = By.cssSelector("div.select-search__select button");

    private final By firstNameInput = By.xpath("//input[contains(@placeholder, 'Имя')]");
    private final By lastNameInput = By.xpath("//input[contains(@placeholder, 'Фамилия')]");
    private final By addressInput = By.xpath("//input[contains(@placeholder, 'Адрес')]");
    private final By phoneInput = By.xpath("//input[contains(@placeholder, 'Телефон')]");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    private final By commentInput = By.xpath("//input[contains(@placeholder, 'Комментарий')]");
    private final By blackColorCheckbox = By.cssSelector("input#black");
    private final By greyColorCheckbox = By.cssSelector("input#grey");
    private final By rentalDropdown = By.cssSelector("div.Dropdown-root");
    private final By rentalOptions = By.cssSelector("div.Dropdown-menu div.Dropdown-option");
    private final By deliveryDateInput = By.xpath("//input[contains(@placeholder, 'Когда привезти')]");
    private final By orderButton = By.xpath("//button[contains(@class, 'Button_Middle') and text()='Заказать']");
    private final By confirmButton = By.xpath("//button[text()='Да']");

    private final By successMessage = By.xpath("//div[contains(@class, 'Order_ModalHeader') and contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Было: fillStep1 -> Стало: fillPersonalAndMetroData
    public void fillPersonalAndMetroData(String firstName, String lastName, String address, String metroStation, String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);

        WebElement metroField = driver.findElement(metroInputLocator);
        metroField.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(metroDropdownContainer));

        List<WebElement> options = driver.findElements(metroOptionButtons);
        boolean found = false;
        for (WebElement option : options) {
            String text = option.getText();
            if (text.contains(metroStation)) {
                option.click();
                found = true;
                break;
            }
        }
//
        if (!found) {
            throw new NoSuchElementException("Станция метро '" + metroStation + "' не найдена в списке. Доступные: " +
                    options.stream().map(WebElement::getText).reduce((a, b) -> a + ", " + b).orElse("пусто"));
        }

        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    // Было: fillStep2 -> Стало: fillOrderDetails
    public void fillOrderDetails(String comment, String color, String rentalPeriod, String deliveryDate) {
        driver.findElement(commentInput).sendKeys(comment);

        if ("чёрный жемчуг".equals(color)) {
            driver.findElement(blackColorCheckbox).click();
        } else if ("серая безысходность".equals(color)) {
            driver.findElement(greyColorCheckbox).click();
        }

        driver.findElement(rentalDropdown).click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rentalOptions));
        for (WebElement option : options) {
            if (option.getText().equals(rentalPeriod)) {
                option.click();
                break;
            }
        }

        driver.findElement(deliveryDateInput).sendKeys(deliveryDate);
    }

    public void clickOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    public void clickConfirm() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    public boolean isOrderSuccessVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}



