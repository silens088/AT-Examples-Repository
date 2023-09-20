package RestAssuredApi.Reqresapp.withPojo.PostLogin;

import RestAssuredApi.Reqresapp.withPojo.CustomListener;
import RestAssuredApi.Reqresapp.withPojo.Specification;
import config.ApiConfigLoader;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


@Owner("velichkovv")
@Listeners(CustomListener.class)
public class PostLoginTests {

    @DataProvider(name = "loginData")
    public Object[][] dataProviderPositive() {
        return new Object[][]{
                {"eve.holt@reqres.in", "cityslicka", "QpwL5tke4Pnpja7X4"},
                //другие комбинации email, пароля и ожидаемого токена
        };
    }

    @Test(dataProvider = "loginData")
    @Link("some links")
    @Epic("Login")
    @Feature("Login user")
    @Story("Авторизация пользователя")
    @Description("Тест проверяет успешный логин")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("POST"), @Tag("Login")})
    public void createNewUserTest(String email, String password, String expectedToken) {
        Specification.InstallSpecification(Specification.requestSpec(ApiConfigLoader.getProperty("BASE_URL")), Specification.responseSpecOK200());

        PostLoginRequest request = new PostLoginRequest();
        request.setEmail(email);
        request.setPassword(password);

        step("Проверяем успешный логин", () -> {
            PostLoginResponse response = given()
                    .filter(new AllureRestAssured())                                                                        // Добавляем AllureRestAssured для интеграции с Allure
                    .body(request)
                    .when()
                    .post(ApiConfigLoader.getProperty("LOGIN_API"))
                    .then().log().all()
                    .body(matchesJsonSchemaInClasspath("response-schema/response-schema-PostCreateUserTest.json"))
                    .extract().as(PostLoginResponse.class);

            Assert.assertEquals(expectedToken, response.getToken());
        });
    }

    @DataProvider(name = "negativeLoginData")
    public Object[][] dataProviderNegative() {
        return new Object[][]{
                //{"wrong.email@reqres.in", "cityslicka", "Login failed: Wrong email"},
                {"-1", "wrongPassword", "user not found"},
        };
    }

    @Test(dataProvider = "negativeLoginData")
    @Tag("POST")
    @Link("some links")
    @Epic("Login")
    @Feature("Request to login")
    @Story("Неудачная авторизация пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("check fail login test")
    public void negativeLoginTest(String email, String password, String expectedErrorMessage) {
        Specification.InstallSpecification(Specification.requestSpec(ApiConfigLoader.getProperty("BASE_URL")), Specification.responseSpecOK400());  // Этот метод может измениться, если для негативных сценариев ожидается другой статус ответа.

        PostLoginRequest request = new PostLoginRequest();
        request.setEmail(email);
        request.setPassword(password);

        step("Проверяем неудачную попытку логина", () -> {
            Response response = given()
                    .filter(new AllureRestAssured())
                    .body(request)
                    .when()
                    .post(ApiConfigLoader.getProperty("LOGIN_API"))
                    .then().log().all()
                    .extract().response();

            String actualErrorMessage = response.jsonPath().getString("error");
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        });
    }
}

