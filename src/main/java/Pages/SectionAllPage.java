package Pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class SectionAllPage {
    private final ElementsCollection sectionsAll = $$x("//div[@class='hmenu-item hmenu-title ']");

    @Step("Получить элементы ввиде текста из секции 'All'")
    public List<String> getDivElementsFromSectionAll() {
        return sectionsAll.texts();
    }
}
