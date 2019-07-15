package dto;

import data.Users;

import java.util.Map;

public class SignUpUser {
    private String username;
    private String password;

    private Map<String, String> users;

    private boolean emptyUsername;
    private boolean emptyPassword;
    private boolean repeatedUsername;

    private String labelUsername;
    private String labelPassword;
    private String labelAdmin;
    private String labelNormalUser;
    private String labelRepeatedUsername;

    public SignUpUser(String username, String password, String userType) {
        this.username = username.trim();
        this.password = password.trim();

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

        if (!emptyUsername)
            repeatedUsername = users.containsKey(username);
        else
            repeatedUsername = false;

        labelUsername = emptyUsername ? "You must input " : "";
        labelPassword = emptyPassword ? "You must input " : "";
        labelRepeatedUsername = repeatedUsername ? "Username already been used!" : "";
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

    public Map<String, String> getUsers() {
        return users;
    }

    public boolean isEmptyUsername() {
        return emptyUsername;
    }

    public boolean isEmptyPassword() {
        return emptyPassword;
    }

    public boolean isRepeatedUsername() {
        return repeatedUsername;
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

    public String getLabelRepeatedUsername() {
        return labelRepeatedUsername;
    }
}
