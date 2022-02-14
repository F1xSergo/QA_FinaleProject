package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AddressPage {
    private final SelenideElement manageAddressBook = $(byText("Manage address book"));
    private final SelenideElement addAddressButton = $x("//div[@id='ya-myab-plus-address-icon']");
    private final SelenideElement removeAddressButton = $x("//a[@id='ya-myab-address-delete-btn-0']");
    private final SelenideElement buttonYes = $x("//span[@id='deleteAddressModal-0-submit-btn']");
//    private final SelenideElement modalRemoveAddress = $x("//div[@id='a-popover-2']");
//    private final SelenideElement modalAddAddress = $x("//div[@class='a-section a-spacing-medium a-text-left address-narrow-container-desktop']");

    private final SelenideElement addCountry = $x("//span[@class='a-button-text a-declarative'][@data-action='a-dropdown-button']");
    private final SelenideElement Belarus = $x("//a[@id='address-ui-widgets-countryCode-dropdown-nativeId_20']");
    private final SelenideElement addFullName = $x("//input[@type='text'][@id='address-ui-widgets-enterAddressFullName']");
    private final SelenideElement addStreetAddress = $x("//input[@type='text'][@id='address-ui-widgets-enterAddressLine1']");
    private final SelenideElement addCity = $x("//input[@type='text'][@id='address-ui-widgets-enterAddressCity']");
    private final SelenideElement addZipCode = $x("//input[@type='text'][@id='address-ui-widgets-enterAddressPostalCode']");
    private final SelenideElement addPhoneNumber = $x("//input[@type='text'][@id='address-ui-widgets-enterAddressPhoneNumber']");
    private final SelenideElement buttonAddAddress = $x("//input[@class='a-button-input']");

    private final SelenideElement bannerAddressSaved = $x("//h4[@class='a-alert-heading']");


    public AddressPage goToAddressForm() {
        manageAddressBook.hover().click();
        return this;
    }

    public AddressPage addAddressButton() {
        addAddressButton.click();
        return this;
    }

    public AddressPage removeAddressButton() throws InterruptedException {
        removeAddressButton.click();
        Thread.sleep(1000);
        //actions().moveToElement(buttonYes).click(buttonYes).perform();
        buttonYes.hover().click();
        return this;
    }

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

    public String checkAddressSave() {
        return bannerAddressSaved.text();
    }
}
