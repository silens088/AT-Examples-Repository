package RestAssuredApi.Reqresapp.withPojo.PostLogin;

import RestAssuredApi.Reqresapp.withPojo.CustomListener;
import RestAssuredApi.Reqresapp.withPojo.Specification;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


@Owner("velichkovv")
@Listeners(CustomListener.class)
public class PostLoginTests {

    private final String URL_MAIN = "https://reqres.in/";
    private final String LOGIN_API = "/api/login";

    @Test
    @Tag("POST")
    @Link("some links")
    @Epic("Login")
    @Feature("Отправляем запрос на логин")
    @Story("Авторизация пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет успешный логин")
    public void createNewUserTest() {
        Specification.InstallSpecification(Specification.requestSpec(URL_MAIN), Specification.responseSpecOK200());
        PostLoginRequest request = new PostLoginRequest();
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("cityslicka");
        String actualTokenData = "QpwL5tke4Pnpja7X4";

        step("Проверяем успешный логин", () -> {
            PostLoginResponse response = given()
                    .filter(new AllureRestAssured())                                                                        // Добавляем AllureRestAssured для интеграции с Allure
                    .body(request)
                    .when()
                    .post(LOGIN_API)
                    .then().log().all()
                    .body(matchesJsonSchemaInClasspath("response-schema/response-schema-PostCreateUserTest.json"))
                    .extract().as(PostLoginResponse.class);

            Assert.assertEquals(actualTokenData, response.getToken());
        });
    }
}