package RestAssuredApi.Reqresapp.withPojo.GetListUsers;

import RestAssuredApi.Reqresapp.withPojo.Specification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.FileAssert.fail;

public class GetListUsersApiTest {

    public String URL_MAIN = "https://reqres.in/";
    public String USERS_LIST_PAGE1 = "/api/users?page=2";

    @Test
    @Description("Тест проверяет поля Json файла, полученного в ответе")
    public void getAndCheckUsersListTest() {
        Specification.InstallSpecification(Specification.requestSpec(URL_MAIN), Specification.responseSpecOK200());

        Integer page = 1;
        Integer per_page = 6;
        Integer total = 12;
        Integer total_pages = 2;
        String urlSupport = "https://reqres.in/#support-heading";
        String textSupport = "To keep ReqRes free, contributions towards server costs are appreciated!";
        String endsWith = "@reqres.in";

        GetListUsers.Root rootData = given()
                .when()
                .get(USERS_LIST_PAGE1)
                .then().log().all()
                .body(matchesJsonSchemaInClasspath("response-schema/response-schema-GetListUsersApiTest.json"))
                .extract().as(GetListUsers.Root.class);

        //Root
        Assert.assertEquals(rootData.getPage(), page);
        Assert.assertEquals(rootData.getPer_page(), per_page);
        Assert.assertEquals(rootData.getTotal(), total);
        Assert.assertEquals(rootData.getTotal_pages(), total_pages);
        //Support
        Assert.assertEquals(rootData.getSupport().getUrl(), urlSupport);
        Assert.assertEquals(rootData.getSupport().getText(), textSupport);
        //Datum
        rootData.getData().stream().forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));      //проверка что у аватара есть ид (стрим проверки разобрать)
        Assert.assertTrue(rootData.getData().stream().allMatch(x -> x.getEmail().endsWith(endsWith)));                  //проверка что email оканчивается на @reqres.in


        //тест на проверку элемента 2
        Response response = given()
                .when()
                .get(USERS_LIST_PAGE1)
                .then()
                .statusCode(200)
                .extract().response();

        // Извлекаем список из ответа
        JsonPath jsonPath = new JsonPath(response.asString());
        List<Map<String, Object>> dataList = jsonPath.getList("data");

        // Находим конкретный объект в списке по ID
        Optional<Map<String, Object>> userOptional = dataList.stream()
                .filter(user -> user.get("id").equals(2))
                .findFirst();

        // Проверяем поля объекта
        if (userOptional.isPresent()) {
            Map<String, Object> user = userOptional.get();
            assertThat(user.get("email"), equalTo("janet.weaver@reqres.in"));
            assertThat(user.get("first_name"), equalTo("Janet"));
            assertThat(user.get("last_name"), equalTo("Weaver"));
            assertThat(user.get("avatar"), equalTo("https://reqres.in/img/faces/2-image.jpg"));
            //fail("Всё нормально в этом тесте");
        } else {
            fail("Пользователь с ID 2 не найден!");
        }

    }
}