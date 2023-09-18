package RestAssuredApi.Reqresapp.withPojo.PostCreateUser;

import java.util.Date;

public class PostCreateUserResponse {

    public String name;
    public String job;
    public String id;
    public Date createdAt;

    public PostCreateUserResponse() {

    }

    public PostCreateUserResponse(String name, String job, String id, Date createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }



    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}