package org.ce.ap.server;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
/**
 * class :The type Client.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class Client implements Serializable {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private PersonalInformation personalInformation;

    /**
     * constructor of client
     *
     * @param firstName           first name of client
     * @param lastName            last name of client
     * @param birthday            client's date of birth
     * @param personalInformation the personal information about logging in
     */
    public Client(String firstName, String lastName, LocalDate birthday, PersonalInformation personalInformation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.personalInformation = personalInformation;

    }


    /**
     * compares an object to this client
     *
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


    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets birthday.
     *
     * @return the birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Sets birthday.
     *
     * @param birthday the birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Get user name string.
     *
     * @return the string
     */
    public String getUserName() {
        return personalInformation.getUserName();
    }

    /**
     * Get password string.
     *
     * @return the string
     */
    public String getPassword() {
        return personalInformation.getPassword();
    }

    /**
     * Sets personal information.
     *
     * @param personalInformation the personal information
     */
    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }


}