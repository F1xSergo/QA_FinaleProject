package Ui;


import Pages.CartPage;
import Pages.MainPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

public class SiteOperationsTests extends MainTest {
    private final static String BASE_URL = "https://www.amazon.com/";
    private final static List<String> EXPECTED_ELEMENTS_IN_SECTION_ALL =
            new ArrayList<>(Arrays.asList("Digital Content & Devices", "Shop By Department", "Programs & Features", "Help & Settings"));

    @Description("Тест проверяет или в секции находятся нужные элементы")
    @DisplayName("Тест проверяет или в секции находятся нужные элементы")
    @Test
    public void testSectionAll() {
        List<String> elementsInSectionAll = new MainPage(BASE_URL)
                .enterInSectionAll()
                .getDivElementsFromSectionAll();
        Assertions.assertTrue(elementsInSectionAll.containsAll(EXPECTED_ELEMENTS_IN_SECTION_ALL));
    }

    @Description("Тест проверяет или в секции 'Monitors' находятся товары")
    @DisplayName("Тест проверяет или в секции 'Monitors' находятся товары")
    @Test
    public void testNavigationThroughSection() {
        SelenideElement sectionMonitors = new MainPage(BASE_URL)
                .navigationToSectionMonitors()
                .sectionMonitorsIsNotEmpty();

        Assertions.assertTrue(sectionMonitors.exists());
    }

    @Description("Тест проверят или юзер находит товар через поиск, кладет его в корзину и проверят, что в корзине именно тот товар")
    @DisplayName("Тест проверят или юзер находит товар через поиск, кладет его в корзину и проверят, что в корзине именно тот товар")
    @CsvSource({"Lg ultragear, 'LG 27GL850-B 27 Inch Ultragear QHD Nano IPS 1ms NVIDIA G-Sync Compatible Gaming Monitor, Black'"})
    @ParameterizedTest
    public void testSearchItem(String serchItem, String expectedNameInCart) {
        CartPage cart = new MainPage(BASE_URL)
                .enterTextToSearchString(serchItem)
                .selectItemByText(expectedNameInCart)
                .addToCart()
                .openCart();

        String actualNameInCart = cart.getItemNameInCart();
//        Assertions.assertEquals(expectedNameInCart, actualNameInCart);

        assertAll(
                //проверка что в корзине не пусто
                () -> Assertions.assertTrue(cart.checkCartIsNotEmpty()),
                //проверяем что содержится нужное имя(необязательно)
                () -> Assertions.assertTrue(cart.checkItemExistInCart(expectedNameInCart)),
                //сравниваем что в корзине именно ожидаемы результат совпадает с актульным
                () -> Assertions.assertEquals(expectedNameInCart, actualNameInCart)
        );
    }
}
