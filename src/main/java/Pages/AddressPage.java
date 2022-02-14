package Pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AddressPage {
    private final SelenideElement manageAddressBook = $(byText("Manage address book"));
    private final SelenideElement addAddressButton = $x("//div[@id='ya-myab-plus-address-icon']");
    private final SelenideElement removeAddressButton = $x("//a[@id='ya-myab-address-delete-btn-0']");
    private final SelenideElement buttonYes = $x("//span[@id='deleteAddressModal-0-submit-btn']");

    private final SelenideElement addCountry = $x("//span[@class='a-button-text a-declarative'][@data-action='a-dropdown-button']");
    private final SelenideElement Belarus = $x("//a[@id='address-ui-widgets-countryCode-dropdown-nativeId_20']");
    private final SelenideElement addFullName = $x("//input[@type='text'][@id='address-ui-widgets-enterAddressFullName']");
    private final SelenideElement addStreetAddress = $x("//input[@type='text'][@id='address-ui-widgets-enterAddressLine1']");
    private final SelenideElement addCity = $x("//input[@type='text'][@id='address-ui-widgets-enterAddressCity']");
    private final SelenideElement addZipCode = $x("//input[@type='text'][@id='address-ui-widgets-enterAddressPostalCode']");
    private final SelenideElement addPhoneNumber = $x("//input[@type='text'][@id='address-ui-widgets-enterAddressPhoneNumber']");
    private final SelenideElement buttonAddAddress = $x("//input[@class='a-button-input']");

    private final SelenideElement bannerAddress = $x("//h4[@class='a-alert-heading']");

    @Step("переход в секцию 'Address'")
    public AddressPage goToAddressForm() throws InterruptedException {
        Thread.sleep(500);
        manageAddressBook.hover().click();
        return this;
    }

    @Step("Нажимаем на плюсик в секции 'Address'")
    public AddressPage addAddressButton() {
        addAddressButton.click();
        return this;
    }

    @Step("Нажимаем кнопку 'removeAddress'  и кнопку 'Yes'")
    public AddressPage removeAddressButton() throws InterruptedException {
        removeAddressButton.click();
        Thread.sleep(1000);
        //actions().moveToElement(buttonYes).click(buttonYes).perform();
        buttonYes.hover().click();
        return this;
    }

    @Step("Заполняем форму добавления адреса")
    public AddressPage enterAddressDetails(String name, String street, String city, String index, String number) throws InterruptedException {
        addCountry.click();
        Belarus.click();
        Thread.sleep(2000);
        addFullName.setValue(name);
        addStreetAddress.setValue(street);
        addCity.setValue(city);
        addZipCode.setValue(index);
        addPhoneNumber.setValue(number);

        buttonAddAddress.click();
        return this;
    }

    @Step("Проверяем баннер с уведомлением о статусе адреса")
    public String checkAddressSave() {
        return bannerAddress.text();
    }
}
