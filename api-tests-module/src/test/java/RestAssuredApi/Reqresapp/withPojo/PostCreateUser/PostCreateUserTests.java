package RestAssuredApi.Reqresapp.withPojo.PostCreateUser;

import RestAssuredApi.Reqresapp.withPojo.Specification;
import config.ApiConfigLoader;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostCreateUserTests {

    @Test
    @Description("Тест проверяет создание нового пользователя")
    public void createNewUserTest() {
        PostCreateUserRequest request = new PostCreateUserRequest();
        request.setName("Piter");
        request.setJob("photographer");
        Specification.InstallSpecification(Specification.requestSpec(ApiConfigLoader.getProperty("BASE_URL")), Specification.responseSpecOK201());

        PostCreateUserResponse response = given()
                .body(request)
                .when()
                .post(ApiConfigLoader.getProperty("POST_CREATE_USER_API"))
                .then().log().all()
                .body(matchesJsonSchemaInClasspath("response-schema/response-schema-PostCreateUserTest.json"))
                .extract().as(PostCreateUserResponse.class);

        Assert.assertEquals(request.getName(), response.getName());
        Assert.assertEquals(request.getJob(), response.getJob());
        Assert.assertTrue(response.getId().matches("^\\d+$"));
        Assert.assertNotNull(response.getCreatedAt());
    }
}