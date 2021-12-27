package main.java.org.ce.ap.client;

//import org.json.JSONObject;

import main.java.org.ce.ap.server.PersonalInformation;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Objects;



public class Client {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private PersonalInformation personalInformation;

    /** constructor of client
     * @param firstName first name of client
     * @param lastName last name of client
     * @param birthday client's date of birth
     * @param personalInformation the personal information about logging in
     */
    public Client(String firstName, String lastName, LocalDate birthday ,PersonalInformation personalInformation)  {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.personalInformation=personalInformation;

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
        return Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(birthday, client.birthday) && Objects.equals(personalInformation, client.personalInformation);
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

    public String getUserName(){
        return personalInformation.getUserName();
    }
    public String getPassword(){
        return personalInformation.getPassword();
    }
    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }




}