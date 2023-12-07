package Model.Staff;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("status")
    private String status;

    public User(){}
    public User(String username, String password, String status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", status='" + status + '\'' + '}';
    }

    public void setUserName(String userName) {

        this.username = userName;
    }

    public String getUserName() {
        return username;
    }

    public String getStatus() {
        return status;
    }
}
