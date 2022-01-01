package main.java.org.ce.ap.server;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

/**
 * class : The type Personal information.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class PersonalInformation implements Serializable {

    private String userName;
    private String password;

    /**
     * Instantiates a new Personal information.
     *
     * @param userName the user name
     * @param password the password
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public PersonalInformation(String userName, String password) throws NoSuchAlgorithmException {
        this.userName = userName;
        this.password = GFG.toHexString(GFG.getSHA(password));
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
