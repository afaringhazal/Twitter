import java.security.NoSuchAlgorithmException;

public class PersonalInformation {

    private String userName;
    private String password;

    public PersonalInformation(String userName, String password) throws NoSuchAlgorithmException {
        this.userName = userName;
        this.password = GFG.toHexString(GFG.getSHA(password));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
