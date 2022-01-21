package org.ce.ap.impl.server;
import org.ce.ap.server.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * class : The type Authentication service.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    /**
     * The Logger.
     */
    Logger logger;
    /**
     * The Database.
     */
    Database database;


    /**
     * Instantiates a new Authentication service.
     *
     * @param database the database
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public AuthenticationServiceImpl(Database database) throws NoSuchAlgorithmException {
        this.database = database;
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    }

    /**
     * Sign up request personal information.
     *
     * @param userName the user name
     * @param password the password
     * @return the personal information
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    @Override
    public synchronized PersonalInformation signUpRequest(String userName, String password) throws NoSuchAlgorithmException {
        for (Client client : database.getClients()) {
            if (client.getUserName().equals(userName)) {
                logger.info("username " + userName + "was requested for sign up but  already existed in database.");
                return null;
            }
        }
        logger.info("username " + userName + "was requested for sign up and was allowed to sign up.");
        return new PersonalInformation(userName, password);
    }

    /**
     * Sign in request page.
     *
     * @param username the username
     * @param password the password
     * @return the page
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    @Override
    public synchronized Page signInRequest(String username, String password) throws NoSuchAlgorithmException {
        String hashedPass = GFG.toHexString(GFG.getSHA(password));
        for (Client client : database.getClients()) {
            if (client.getUserName().equals(username) && client.getPassword().equals(hashedPass)) {
                logger.info("username " + username + "was requested for sign in and was allowed to sign in.");
                return database.getClientPage(client);

            }
        }
        logger.info("username " + username + "was requested for sign in but the input was wrong.");
        return null;
    }

    /**
     * Sign up page.
     *
     * @param client the client
     * @param id     the id
     * @param bio    the bio
     * @return the page
     */
    @Override
    public synchronized Page signUp(Client client, String id, String bio) {
        logger.info("username " + client.getUserName() + "was signed up.");
        Page page = new Page(client, id, bio, LocalDate.now());
        database.getClients().add(client);
        database.getClientIds().put(client.getUserName(), client);
        database.getAllClientPages().put(client, page);
        return page;
    }

    /**
     * All user names array list.
     *
     * @return the array list
     */
    @Override
    public synchronized ArrayList<String> AllUserNames() {
        logger.info("all usernames were returned.");
        return database.getUserNames();

    }
}