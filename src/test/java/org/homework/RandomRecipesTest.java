package org.homework;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта spoonacular.com")
@Feature("Тестирование API Random API")
public class RandomRecipesTest extends AbstractTest {

    @Test
    @DisplayName("Тест RandomRecipesTest - тестирование на получение рандомного рецепта")
    @Description("Данный тест предназначен для поиска Random рецепта")
    @Link("@https://api.spoonacular.com/recipes/")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Вызов метода Random для объекта Recipe")
    @Owner("Гильманов Олег")
    public void testGetRecipesRandomly() {
        GetRandomRecipes response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("number", 4)
                .when()
                .get(getBaseUrl() + "random")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(3000L))
                .extract()
                .response()
                .body().as(GetRandomRecipes.class);

        Assertions.assertEquals(response.recipes.size(),4);
    }
}
