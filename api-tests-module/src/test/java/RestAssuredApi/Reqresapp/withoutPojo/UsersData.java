package RestAssuredApi.Reqresapp.withoutPojo;

import java.util.HashMap;
import java.util.Map;

public class UsersData {

    public String URL_MAIN = "https://reqres.in/";
    public String USERS_LIST_PAGE1 = "/api/users?page=1";
    public String USER_TWO = "/api/users/5";
    public String REGISTER_USER_API = "/api/register";

    public Map<String, String> getRegistrationUserDataList() { //json в запросе отправляется как хэшмап
        Map<String, String> registrationUserData = new HashMap<>();
        registrationUserData.put("email", "eve.holt@reqres.in");
        registrationUserData.put("password", "pistol");
        return registrationUserData;
    }
}