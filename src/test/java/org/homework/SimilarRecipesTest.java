package org.homework;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта spoonacular.com")
@Feature("Тестирование API Similar API")
public class SimilarRecipesTest extends AbstractTest {

    @Test
    @DisplayName("Тестирование SimilarRecipesTest на ответ код 200 получение рецепта " +
            "Grilled Romesco-Style Pork и " +
            "Dinner Tonight: Chickpea Bruschetta")
    @Description("Данный тест предназначен для поиска схожих рецептов")
    @Link("@https://api.spoonacular.com/recipes/")
    @Severity(SeverityLevel.NORMAL)
    @Story("Вызов метода Similar для объекта Recipe")
    @Owner("Гильманов Олег")
    void getSimilarRecipes_whenValid_shouldReturn() {
        List<GetSimilarRecipe> response = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl()+"715538/similar")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", GetSimilarRecipe.class);

        Assertions.assertEquals(response.size(),10);
        response.forEach(v -> {
            if (v.getId().equals(209128)) {
                Assertions.assertEquals(v.getTitle(),"Dinner Tonight: Grilled Romesco-Style Pork");
            }
            if (v.getId().equals(31868)) {
                Assertions.assertEquals(v.getTitle(),"Dinner Tonight: Chickpea Bruschetta");
            }
        });
    }
}
