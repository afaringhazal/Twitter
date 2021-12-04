import java.time.LocalDate;
import java.util.Objects;

//username => server
public class Client {

    private String firstName;
    private String lastName;
    private LocalDate age;
    private String biography;
    private LocalDate joinDate;
    private String userName;
    private GFG password ;

    public Client(String firstName, String lastName, LocalDate age, String biography, LocalDate joinDate, String userName, GFG password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.biography = biography;
        this.joinDate = joinDate;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(age, client.age) && Objects.equals(biography, client.biography) && Objects.equals(joinDate, client.joinDate) && Objects.equals(userName, client.userName) && Objects.equals(password, client.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, biography, joinDate, userName, password);
    }

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

    public LocalDate getAge() {
        return age;
    }

    public void setAge(LocalDate age) {
        this.age = age;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public GFG getPassword() {
        return password;
    }

    public void setPassword(GFG password) {
        this.password = password;
    }
}
