package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.data.OrderData;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderPage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderFlowTest extends BaseTest {

    private MainPage mainPage;
    private OrderPage orderPage;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String comment;
    private final String color;
    private final String rentalPeriod;
    private final String deliveryDate;

    public OrderFlowTest(String firstName, String lastName, String address, String metro, String phone,
                         String comment, String color, String rentalPeriod, String deliveryDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.comment = comment;
        this.color = color;
        this.rentalPeriod = rentalPeriod;
        this.deliveryDate = deliveryDate;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return OrderData.getOrderData();
    }

    @Before
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void checkOrderFlow() {
        mainPage.open();
        mainPage.clickOrderButton();

        orderPage.fillPersonalAndMetroData(firstName, lastName, address, metro, phone);
        orderPage.clickNext();

        orderPage.fillOrderDetails(comment, color, rentalPeriod, deliveryDate);
        orderPage.clickOrder();
        orderPage.clickConfirm();

        assertThat(orderPage.isOrderSuccessVisible()).isTrue();
    }

    @Test
    public void checkOrderFlowWithSecondButton() {
        mainPage.open();
        mainPage.clickOrderButton();

        orderPage.fillPersonalAndMetroData(firstName, lastName, address, metro, phone);
        orderPage.clickNext();

        orderPage.fillOrderDetails(comment, color, rentalPeriod, deliveryDate);
        orderPage.clickOrderWithSpecificClass();
        orderPage.clickConfirm();

        assertThat(orderPage.isOrderSuccessVisible()).isTrue();
    }
}
