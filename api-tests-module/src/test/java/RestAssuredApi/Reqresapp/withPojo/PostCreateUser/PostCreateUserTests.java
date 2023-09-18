package RestAssuredApi.Reqresapp.withPojo.PostCreateUser;

import RestAssuredApi.Reqresapp.withPojo.Specification;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostCreateUserTests {

    public final String URL_MAIN = "https://reqres.in/";
    public final String CREATE_USER_API = "/api/users";

    @Test
    @Description("Тест проверяет создание нового пользователя")
    public void createNewUserTest() {
        PostCreateUserRequest request = new PostCreateUserRequest();
        request.setName("Piter");
        request.setJob("photographer");
        Specification.InstallSpecification(Specification.requestSpec(URL_MAIN), Specification.responseSpecOK201());

        PostCreateUserResponse response = given()
                .body(request)
                .when()
                .post(CREATE_USER_API)
                .then().log().all()
                .body(matchesJsonSchemaInClasspath("response-schema/response-schema-PostCreateUserTest.json"))
                .extract().as(PostCreateUserResponse.class);

        Assert.assertEquals(request.getName(), response.getName());
        Assert.assertEquals(request.getJob(), response.getJob());
        Assert.assertTrue(response.getId().matches("^\\d+$"));
        Assert.assertNotNull(response.getCreatedAt());
    }
}