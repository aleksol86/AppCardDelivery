package ru.netology.domain;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

    @Test
    void shouldSuccessfulSendForm() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateForTest = LocalDate.now().plusDays(3);
        $("input[placeholder=\"Дата встречи\"]").sendKeys(Keys.LEFT_CONTROL + "a" + Keys.BACK_SPACE);
        $("input[placeholder=\"Дата встречи\"]").val(dateForTest.format(formatter));
        $("[data-test-id=name] input").setValue("Медведев Николай");
        $("[data-test-id=phone] input").setValue("+79251234122");
        $("[data-test-id=agreement]").click();
        $$("button[type=\"button\"]").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }
}
