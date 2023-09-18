package RestAssuredApi.null_.testnewtest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class mainTest {
    @BeforeTest
    public static void setBaseUrl() {
        RestAssured.baseURI = "https://api.spacexdata.com/v4";
    }

    @Test
    public void checkCeoIsElonMusk() {
        given().get("/company")
                .then().log().body()
                .body("ceo", equalTo("Elon Musk"));

    }
}

