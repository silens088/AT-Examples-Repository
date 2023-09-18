package RestAssuredApi.Reqresapp.withPojo.GetListUsers;

import java.util.ArrayList;

public class GetListUsers {

    public static class Datum{
        public Integer id;
        public String email;
        public String first_name;
        public String last_name;
        public String avatar;

        public Datum(Integer id, String email, String first_name, String last_name, String avatar) {
            this.id = id;
            this.email = email;
            this.first_name = first_name;
            this.last_name = last_name;
            this.avatar = avatar;
        }

        public Datum() {
        }

        public Integer getId() {
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

    public static class Root{
        public Integer page;
        public Integer per_page;
        public Integer total;
        public Integer total_pages;
        public ArrayList<Datum> data;
        public Support support;

        public Root(Integer page, Integer per_page, Integer total, Integer total_pages, ArrayList<Datum> data, Support support) {
            this.page = page;
            this.per_page = per_page;
            this.total = total;
            this.total_pages = total_pages;
            this.data = data;
            this.support = support;
        }

        public Root() {
        }

        public Integer getPage() {
            return page;
        }

        public Integer getPer_page() {
            return per_page;
        }

        public Integer getTotal() {
            return total;
        }

        public Integer getTotal_pages() {
            return total_pages;
        }

        public ArrayList<Datum> getData() {
            return data;
        }

        public Support getSupport() {
            return support;
        }
    }

    public static class Support{
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
