package ru.netology;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

        int days = 3;
        ChooseDate chooseDate = new ChooseDate();


        @Test
        void testSelenide() {
                Configuration.holdBrowserOpen = true;
                open("http://localhost:9999/");
                $("[data-test-id='city'] input").setValue("Екатеринбург");
                $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
                $("[data-test-id='date'] input").setValue(chooseDate.generateDate(days));
                $("[data-test-id='name'] input").setValue("Алексеев Максим");
                $("[data-test-id='phone'] input").setValue("+79969527358");
                $("[data-test-id='agreement']").click();
                $$("button").find(exactText("Забронировать")).click();
                $(".notification__content").shouldBe(text("Встреча успешно забронирована на " + ChooseDate.generateDate(days)), Duration.ofSeconds(15)).shouldBe(visible);
        }
}
