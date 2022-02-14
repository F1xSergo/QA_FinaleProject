package Pages;

import com.codeborne.selenide.ElementsCollection;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class SectionAllPage {
    private final ElementsCollection sectionsAll = $$x("//div[@class='hmenu-item hmenu-title ']");

    public List<String> getDivElementsFromSectionAll() {
        return sectionsAll.texts();
    }
}
