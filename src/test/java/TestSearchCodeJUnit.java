import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestSearchCodeJUnit {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {
        open("");
        $(".mr-2.color-fg-muted").click();
        $("#query-builder-test").setValue("Selenide").pressEnter();
        $("[href='/selenide/selenide']").click();
        $("#wiki-tab").shouldBe(visible).click();
        $("#wiki-pages-filter").click();
        $("#wiki-pages-filter").setValue("Soft");
        $(".js-wiki-sidebar-toggle-display").shouldHave(text("SoftAssertions"));
        $(".js-wiki-sidebar-toggle-display").$(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
        $("#wiki-body").shouldHave(text("class Tests {\n" +
                "@RegisterExtension\n" +
                "static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                "\n" +
                "@Test\n" +
                "void test() {\n" +
                "Configuration.assertionMode = SOFT;\n" +
                "open(\"page.html\");\n" +
                "\n" +
                " $(\"#first\").should(visible).click();\n" +
                " $(\"#second\").should(visible).click();\n" +
                "}\n" +
                "}"));
    }

}




