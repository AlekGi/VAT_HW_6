package org.homework;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта spoonacular.com")
@Feature("Тестирование API Taste API")
public class TasteByIdTest extends AbstractTest{

    @Test
    @DisplayName("Тест TasteByIDTest - Получение ответа код 200 на получение рецепта 702060")
    @Description("Данный тест предназначен для получение рецепта по ключу Taste (вкусовые ощущения)")
    @Link("@https://api.spoonacular.com/recipes/")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Вызов метода Taste для объекта Recipe")
    @Owner("Гильманов Олег")
    void getConvertAmounts_whenValid_shouldReturn() {
        TasteByID response = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl()+"702060/tasteWidget.json")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(20000L))
                .extract()
                .response()
                .body().as(TasteByID.class);

        Assertions.assertEquals(response.getSweetness(), 98.61);
        Assertions.assertEquals(response.getSaltiness(), 76.57);

    }
}
