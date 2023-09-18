package RestAssuredApi.Reqresapp.withPojo.PostLogin;

public class PostLoginResponse {

    public String token;

    public PostLoginResponse(String token) {
        this.token = token;
    }

    public PostLoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}