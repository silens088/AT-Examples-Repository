package RestAssuredApi.Reqresapp.withPojo.GetSingleUser;

public class GetSingleUser {

    public static class Data {
        public Integer id;
        public String email;
        public String first_name;
        public String last_name;
        public String avatar;

        public Data(Integer id, String email, String first_name, String last_name, String avatar) {
            this.id = id;
            this.email = email;
            this.first_name = first_name;
            this.last_name = last_name;
            this.avatar = avatar;
        }

        public Data() {
        }

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getAvatar() {
            return avatar;
        }
    }

    public static class Root {
        public Data data;
        public Support support;

        public Root(Data data, Support support) {
            this.data = data;
            this.support = support;
        }

        public Root() {
        }

        public Data getData() {
            return data;
        }

        public Support getSupport() {
            return support;
        }
    }

    public static class Support {
        public String url;
        public String text;

        public Support(String url, String text) {
            this.url = url;
            this.text = text;
        }

        public Support() {
        }

        public String getUrl() {
            return url;
        }

        public String getText() {
            return text;
        }
    }
}
