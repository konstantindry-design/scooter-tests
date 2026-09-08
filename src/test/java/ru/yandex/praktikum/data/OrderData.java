package ru.yandex.praktikum.data;

import java.util.Arrays;
import java.util.List;
//
public class OrderData {
    public static List<Object[]> getOrderData() {
        return Arrays.asList(
                new Object[]{
                        "Иван", "Иванов", "Ленина, 69", "ВДНХ", "+79991235689",
                        "Обвязать бантиком", "чёрный жемчуг", "сутки", "10.09.2026"
                },
                new Object[]{
                        "Пётр", "Петров", "Гагарина, 10", "Охотный Ряд", "+79999876543",
                        "Оставить у консьержа", "серая безысходность", "трое суток", "12.09.2026"
                }
        );
    }
}

