package RestAssuredApi.Reqresapp.withoutPojo;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetListUsersTest {

    public static Logger logger = LoggerFactory.getLogger(GetListUsersTest.class);

    UsersData usersData = new UsersData();

    @Test
    @Description("Тест проверяет полученные поля на соответствие с ожидаемым результатом")
    public void getListUsersTest() {

        List<String> first_name_Data = new ArrayList<>();
        first_name_Data.add("George");
        first_name_Data.add("Janet");
        first_name_Data.add("Emma");
        first_name_Data.add("Eve");
        first_name_Data.add("Charles");
        first_name_Data.add("Tracey");

        List<String> last_name_Data = new ArrayList<String>();
        last_name_Data.add("Bluth");
        last_name_Data.add("Weaver");
        last_name_Data.add("Wong");
        last_name_Data.add("Holt");
        last_name_Data.add("Morris");
        last_name_Data.add("Ramos");

        Response response = given()
                .baseUri(usersData.URL_MAIN)
                .contentType(ContentType.JSON)
                .when()
                .get(usersData.USERS_LIST_PAGE1)
                .then().log().all()
                .body("page", equalTo(1))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .body("data.id", notNullValue())
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"))
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<Integer> dataId = jsonPath.get("data.id");
        List<String> email = jsonPath.get("data.email");
        List<String> first_name = jsonPath.get("data.first_name");
        List<String> last_name = jsonPath.get("data.last_name");
        List<String> avatar = jsonPath.get("data.avatar");

        for (int i = 0; i < email.size(); i++) {
            Assert.assertTrue(email.get(i).contains("@reqres.in"));
            Assert.assertTrue(first_name.get(i).contains(first_name_Data.get(i)));
            Assert.assertTrue(last_name.get(i).contains(last_name_Data.get(i)));
            Assert.assertTrue(avatar.get(i).contains(dataId.get(i).toString()));
        }
    }
}