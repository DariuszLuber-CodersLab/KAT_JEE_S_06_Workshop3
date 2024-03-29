package pl.coderslab.entity;

public class User {

    public User(String userName, String email, String password) {
        setUserName(userName);
        setEmail(email);
        setPassword(password);

    }

    public User() {
    }


    private int id;
    private String userName;
    private String email;
    private String password;
    private int userGroupId;

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
