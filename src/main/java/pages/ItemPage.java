package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;


public class ItemPage {
    private final SelenideElement addToCart = $x("//input[@id='add-to-cart-button']");

    @Step("Добавить в корзину")
    public CartPage addToCart() {
        addToCart.click();
        return new CartPage();
    }
}
