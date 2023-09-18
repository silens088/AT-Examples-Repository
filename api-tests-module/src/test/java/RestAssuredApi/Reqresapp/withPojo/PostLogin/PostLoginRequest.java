package RestAssuredApi.Reqresapp.withPojo.PostLogin;

public class PostLoginRequest {

    private String email;
    private String password;

    public PostLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public PostLoginRequest() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}