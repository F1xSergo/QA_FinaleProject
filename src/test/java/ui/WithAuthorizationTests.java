package ui;

import pages.AddressPage;
import pages.LoginPage;
import pages.MainPage;
import сonfProperties.ConfProperties;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WithAuthorizationTests extends MainTest {
    private final static String BASE_URL = "https://www.amazon.com/";
    private static final String email = ConfProperties.getProperty("email");
    private static final String password = ConfProperties.getProperty("password");

    @Description("Тест проверяет успешна ли авторизация и доп проверка, что авторизован 'Sergey' ")
    @DisplayName("Тест проверяет успешна ли авторизация и доп проверка, что авторизован 'Sergey' ")
    @Test
    public void testAuthorization() {
        LoginPage loginPage = new MainPage(BASE_URL)
                .authorization()
                .enterLogAndPass(email, password);
        String expectedName = loginPage.checkName();

//проверяем что мы авторизованы, и наш юзер Sergey
        Assertions.assertTrue(expectedName.endsWith("Sergey"));
        Assertions.assertTrue(expectedName.contains("Sergey"));
//добавить метод выхода из акк
        new MainPage(BASE_URL)
                .exitFromAccount();
    }

    @Description("Тест проверяет что авторизоция с неправильным логином выводит предупреждение ")
    @DisplayName("Тест проверяет что авторизоция с неправильным логином выводит предупреждение ")
    @Test
    public void testFalseEmailAuthorization() {
        boolean expectedName = new MainPage(BASE_URL)
                .authorization()
                .enterBadLog("BadEmail", password)
                .checkAlertForEmail();
        Assertions.assertTrue(expectedName);
    }

    @Description("Тест проверяет что авторизоция с неправильным паролем выводит предупреждение ")
    @DisplayName("Тест проверяет что авторизоция с неправильным паролем выводит предупреждение ")
    @Test
    public void testFalsePassAuthorization() {
        boolean expectedName = new MainPage(BASE_URL)
                .authorization()
                .enterLogAndPass(email, "BadPassword")
                .checkAlertForPass();
        Assertions.assertTrue(expectedName);
    }

    @Description("Тест проверяет что адрес успешно добавлен")
    @DisplayName("Тест проверяет что адрес успешно добавлен")
    @Test
    public void testAddAddress() throws InterruptedException {
        AddressPage addressPage = new MainPage(BASE_URL)
                .authorization()
                .enterLogAndPass(email, password)
                .enterToAddressSection()
                .goToAddressForm()
                .addAddressButton()
                .enterAddressDetails("Sergey", "Platonova", "Minsk", "220005", "375298003301");
        String actualCheckAddressSaveBanner = addressPage.checkAddressSave();

        Assertions.assertEquals("Address saved", actualCheckAddressSaveBanner);

        new MainPage(BASE_URL)
                .exitFromAccount();
    }

    @Description("Тест проверяет что адрес успешно удален")
    @DisplayName("Тест проверяет что адрес успешно удален")
    @Test
    public void testRemoveAddress() throws InterruptedException {
        AddressPage addressPage = new MainPage(BASE_URL)
                .authorization()
                .enterLogAndPass(email, password)
                .enterToAddressSection()
                .goToAddressForm()
                .removeAddressButton();

        String actualCheckAddressRemoveBanner = addressPage.checkAddressSave();

        Assertions.assertEquals("Address removed", actualCheckAddressRemoveBanner);
        new MainPage(BASE_URL)
                .exitFromAccount();
    }
}
