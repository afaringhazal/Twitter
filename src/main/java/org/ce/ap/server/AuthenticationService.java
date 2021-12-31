package main.java.org.ce.ap.server;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface  AuthenticationService {


    public PersonalInformation signUpRequest(String userName, String password) throws NoSuchAlgorithmException ;

    public Page signInRequest(String username, String password) throws NoSuchAlgorithmException ;


    public Page signUp(Client client, String id, String bio);


    public ArrayList<String> AllUserNames();


}