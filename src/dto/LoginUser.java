package dto;

import data.Users;

import java.util.Map;

public class LoginUser {
    private String username;
    private String password;

    private boolean emptyUsername;
    private boolean emptyPassword;
    private boolean WrongUsernameOrPassword;

    private String labelUsername;
    private String labelPassword;
    private String labelAdmin;
    private String labelNormalUser;
    private String labelWrongUsernameOrPassword;
    private String labelRightUsernameAndPassword;

    public LoginUser(String username, String password, String userType) {
        this.username = username.trim();
        this.password = password.trim();

        Map<String, String> users;

        switch (userType) {
            case "Admin":
                users = Users.admins;
                labelAdmin = "checked";
                labelNormalUser = "";
                break;
            case "NormalUser":
            default:
                users = Users.normalUsers;
                labelAdmin = "";
                labelNormalUser = "checked";
                break;
        }

        emptyUsername = isEmpty(this.username);
        emptyPassword = isEmpty(this.password);

        if (!emptyUsername && !emptyPassword)
            WrongUsernameOrPassword = !password.equals(users.get(username));

        labelUsername = emptyUsername ? "You must input " : "";
        labelPassword = emptyPassword ? "You must input " : "";
        labelWrongUsernameOrPassword = WrongUsernameOrPassword ? "Wrong username or password!" : "";
        labelRightUsernameAndPassword = WrongUsernameOrPassword ? "" : "Welcome! " + userType + " " + this.username + "!";
    }

    private static boolean isEmpty(String field) {
        return "".equals(field);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEmptyUsername() {
        return emptyUsername;
    }

    public boolean isEmptyPassword() {
        return emptyPassword;
    }

    public boolean isWrongUsernameOrPassword() {
        return WrongUsernameOrPassword;
    }

    public String getLabelUsername() {
        return labelUsername;
    }

    public String getLabelPassword() {
        return labelPassword;
    }

    public String getLabelAdmin() {
        return labelAdmin;
    }

    public String getLabelNormalUser() {
        return labelNormalUser;
    }

    public String getLabelWrongUsernameOrPassword() {
        return labelWrongUsernameOrPassword;
    }

    public String getLabelRightUsernameAndPassword() {
        return labelRightUsernameAndPassword;
    }
}
