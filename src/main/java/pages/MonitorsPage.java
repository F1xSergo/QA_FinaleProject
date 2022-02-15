package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MonitorsPage {


    private final SelenideElement resultListMonitors = $x("//div[@class='s-main-slot s-result-list s-search-results sg-row']");
    private final ElementsCollection item = $$x("//div[@class='s-expand-height s-include-content-margin s-latency-cf-section s-border-bottom s-border-top']");

    @Step("проверить что в секции 'Monitors' есть объекты ")
    public SelenideElement sectionMonitorsIsNotEmpty() {
        return resultListMonitors.shouldBe(Condition.exist);
    }
}
