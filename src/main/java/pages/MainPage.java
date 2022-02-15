package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class MainPage {
    private final SelenideElement sectionAll = $x("//a[@id='nav-hamburger-menu']");
    private final SelenideElement sectionComputers = $x("//a[@class='hmenu-item'][@data-menu-id = '6']");
    private final SelenideElement sectionMonitors = $(byText("Monitors"));
    private final SelenideElement searchText = $x("//input[@type='text']");
    private final ElementsCollection itemLink = $$x("//a");
    private final SelenideElement sectionSigIn = $x("//a[@id='nav-link-accountList']");
    //private final SelenideElement buttonSigIn = $x("//a[@class='nav-a nav-a-2   nav-progressive-attribute']");
    private final SelenideElement buttonSigIn = $(byText("Sign in"));
    private final SelenideElement exitButton = $x("//span[contains(text(),'Sign Out')]");


    public MainPage(String url) {
        Selenide.open(url);
    }

    @Step("Войти в секцию 'All'")
    public SectionAllPage enterInSectionAll() {
        sectionAll.scrollIntoView(false).click();
        return new SectionAllPage();
    }
    @Step("Войти в секцию 'Monitors'")
    public MonitorsPage navigationToSectionMonitors() {
        sectionAll.scrollIntoView(false).click();
        sectionComputers.scrollIntoView(false).click();
        sectionMonitors.scrollIntoView(false).click();
        return new MonitorsPage();
    }
    @Step("вводим текст - {0} в поисковике")
    public MainPage enterTextToSearchString(String text) {
        searchText.sendKeys(text, Keys.ENTER);
        return this;
    }
    @Step("находим из результата поиска текст - {0}")
    public ItemPage selectItemByText(String text) {
        itemLink.find(Condition.text(text)).scrollIntoView(false).click();
        return page(ItemPage.class);
    }
    @Step("проходим авторизацию")
    public LoginPage authorization() {
        sectionSigIn.shouldHave(Condition.visible).hover();
        buttonSigIn.click();
        return new LoginPage();
    }
    @Step("Выходим из аккаунта")
    public void exitFromAccount() {
        sectionSigIn.shouldHave(Condition.visible).hover();
        exitButton.click();
    }
}
