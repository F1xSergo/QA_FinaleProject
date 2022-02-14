package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage {
    private final SelenideElement cart = $x("//input[@class='a-button-input'][@type='submit'][@aria-labelledby='attach-sidesheet-view-cart-button-announce']");
    private final SelenideElement itemNameInCart = $x("//span[@class='a-truncate-cut']");
    private final SelenideElement carList = $x("//div[@class='a-fixed-right-grid-col sc-retail-cart-column-spacing a-col-left']");
    private final ElementsCollection itemList = $$x("//div[@class='sc-list-item-content']");

    @Step("Открыть корзину")
    public CartPage openCart() {
        cart.click();
        return this;
    }

    @Step("Получить название товара из корзины")
    public String getItemNameInCart() {
        return itemNameInCart.text();
    }

    @Step("Проверка, что корзина не пустая")
    public boolean checkCartIsNotEmpty() {
        return carList.should(Condition.exist).exists();
    }

    @Step(" проверка что предмет {0} в корзине")
    public boolean checkItemExistInCart(String name) {
        return carList.exists() && itemList.find(Condition.text(name)).exists();
    }
}
