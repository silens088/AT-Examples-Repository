package RestAssuredApi.Reqresapp.withoutPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetSingleUserTest {

    UsersData usersData = new UsersData();

    @Test
    @Description("Тест проверяет что полученные значения совпадают с ожидаемыми значениями")
    public void responseStatusCode() {

        Response response = given()
                .baseUri(usersData.URL_MAIN)
                .contentType(ContentType.JSON)
                .when()
                .get(usersData.USER_TWO)
                .then().log().all()
                .statusCode(200)
                .body("data.id", equalTo(5))
                .body("data.email", equalTo("charles.morris@reqres.in"))
                .body("data.first_name", equalTo("Charles"))
                .body("data.last_name", equalTo("Morris"))
                .body("data.avatar", equalTo("https://reqres.in/img/faces/5-image.jpg"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"))
                .extract().response();
    }

    @Test
    @Description("Как проверять заголовки ???")
    public void responseHead() {

        Response response = given()
                .baseUri(usersData.URL_MAIN)
                .contentType(ContentType.JSON)
                .when()
                .head(usersData.USER_TWO)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }
}