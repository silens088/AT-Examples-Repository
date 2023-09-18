package RestAssuredApi.Reqresapp.withPojo.PostCreateUser;

public class PostCreateUserRequest {

    private String name;
    private String job;

    public PostCreateUserRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public PostCreateUserRequest() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}