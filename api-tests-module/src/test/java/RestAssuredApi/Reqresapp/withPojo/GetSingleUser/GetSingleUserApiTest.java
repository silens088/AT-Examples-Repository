package RestAssuredApi.Reqresapp.withPojo.GetSingleUser;

import RestAssuredApi.Reqresapp.withPojo.Specification;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetSingleUserApiTest {

    public final String URL_MAIN = "https://reqres.in/";
    public final String USER_ONE = "/api/users/1";

    @Test
    @Description("Тест проверяет поля Json в ответе")
    public void getSingleUserTest() {
        Specification.InstallSpecification(Specification.requestSpec(URL_MAIN), Specification.responseSpecOK200());

        Integer idData = 1;
        String email = "george.bluth@reqres.in";
        String first_name = "George";
        String last_name = "Bluth";
        String avatar = "https://reqres.in/img/faces/1-image.jpg";
        String url = "https://reqres.in/#support-heading";
        String text = "To keep ReqRes free, contributions towards server costs are appreciated!";
        String containsText = "To keep ReqRes free";
        String endsWithText = "appreciated!";

        GetSingleUser.Root getDataSingleUser = given()
                .when()
                .get(USER_ONE)
                .then().log().all()
                .body(matchesJsonSchemaInClasspath("response-schema/response-schema-GetSingleUserApiTest.json"))
                .extract().body().as(GetSingleUser.Root.class);


        //Data
        Assert.assertEquals(getDataSingleUser.getData().getId(), idData.intValue());
//        Assert.assertEquals(getDataSingleUser.getData().getEmail(), email);
//        Assert.assertEquals(getDataSingleUser.getData().getFirst_name(), first_name);
//        Assert.assertEquals(getDataSingleUser.getData().getLast_name(), last_name);
//        Assert.assertEquals(getDataSingleUser.getData().getAvatar(), avatar);
//        //Support
//        Assert.assertEquals(getDataSingleUser.getSupport().getUrl(), url);
//        Assert.assertEquals(getDataSingleUser.getSupport().getText(), text);
//        Assert.assertTrue(getDataSingleUser.getSupport().getText().contains(containsText));
//        Assert.assertTrue(getDataSingleUser.getSupport().getText().endsWith(endsWithText));
//        Assert.assertTrue(getDataSingleUser.getSupport().getText().equals(text));
//        Assert.assertFalse(getDataSingleUser.getSupport().getText().isEmpty());
    }
}