package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement emailStr = $x("//input[@id='ap_email']");
    private final SelenideElement passwordStr = $x("//input[@id='ap_password']");
    private final SelenideElement continueButton = $x("//input[@id='continue']");
    private final SelenideElement signInButton = $x("//input[@id='signInSubmit']");
    private final SelenideElement checkUser = $x("//span[@id='nav-link-accountList-nav-line-1'][@class='nav-line-1 nav-progressive-content']");
    private final SelenideElement checkAlertName = $x("//h4[@class='a-alert-heading']");
    private final SelenideElement enterToAddressSection = $x("//a[@id='nav-global-location-popover-link']");

    @Step("Вводим логин и пароль")
    public LoginPage enterLogAndPass(String email, String pass) {
        emailStr.setValue(email).click();
        continueButton.click();
        passwordStr.setValue(pass).click();
        signInButton.click();
        return this;
    }

    @Step("Вводим ложный логин")
    public LoginPage enterBadLog(String email, String pass) {
        emailStr.setValue(email).click();
        continueButton.click();
        return this;
    }

    @Step("Вводим ложный пароль")
    public AddressPage enterToAddressSection() {
        enterToAddressSection.click();
        return new AddressPage();
    }

    @Step("Проверяем какое имя отображается после авторизации")
    public String checkName() {
        return checkUser.text();
    }

    @Step("Проверяем что, при ложном логине вышло сообщение 'There was a problem'")
    public boolean checkAlertForEmail() {
        return checkAlertName.shouldHave(Condition.text("There was a problem")).exists();
    }

    @Step("Проверяем что, при ложном пароле вышло сообщение 'Important Message!' или 'There was a problem'")
    public boolean checkAlertForPass() {
        return checkAlertName.shouldHave(Condition.text("Important Message!")).exists() || checkAlertName.shouldHave(Condition.text("There was a problem")).exists();
    }
}
