package org.ce.ap.server;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * The interface Authentication service.
 */
public interface  AuthenticationService {
    /**
     * Sign up request personal information.
     *
     * @param userName the user name
     * @param password the password
     * @return the personal information
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
     PersonalInformation signUpRequest(String userName, String password) throws NoSuchAlgorithmException;

    /**
     * Sign in request page.
     *
     * @param username the username
     * @param password the password
     * @return the page
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
     Page signInRequest(String username, String password) throws NoSuchAlgorithmException;

    /**
     * Sign up page.
     *
     * @param client the client
     * @param id     the id
     * @param bio    the bio
     * @return the page
     */
     Page signUp(Client client, String id, String bio);

    /**
     * All user names array list.
     *
     * @return the array list
     */
     ArrayList<String> AllUserNames();

}