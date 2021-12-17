import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Objects;



public class Client {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String userName;
    private String password;

    /** constructor of client
     * @param firstName first name of client
     * @param lastName last name of client
     * @param birthday client's date of birth
     * @param userName client's username
     * @param password client's password
     * @throws NoSuchAlgorithmException
     */
    public Client(String firstName, String lastName, LocalDate birthday, String userName, String password) throws NoSuchAlgorithmException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.userName = userName;
        this.password = GFG.toHexString(GFG.getSHA(password));
    }


    /** compares an object to this client
     * @param o the object to compare
     * @return true if two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(birthday, client.birthday) && Objects.equals(userName, client.userName) && Objects.equals(password, client.password);
    }




    //setters and getters


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getUserName() {
        return userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}