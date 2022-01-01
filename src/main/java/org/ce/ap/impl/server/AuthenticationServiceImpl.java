package main.java.org.ce.ap.impl.server;

import main.java.org.ce.ap.server.*;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {

    Logger logger;
    Database database;


    public AuthenticationServiceImpl(Database database) throws NoSuchAlgorithmException {
        this.database = database;
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    }

    @Override
    public synchronized PersonalInformation signUpRequest(String userName, String password) throws NoSuchAlgorithmException {
        for (Client client : database.getClients()) {
            if (client.getUserName().equals(userName)) {
                logger.info("username "+userName+"was requested for sign up but  already existed in database.");
                return null;
            }
        }
        logger.info("username "+userName+"was requested for sign up and was allowed to sign up.");
        return new PersonalInformation(userName, password);
    }

    @Override
    public synchronized Page signInRequest(String username, String password) throws NoSuchAlgorithmException {
        String hashedPass = GFG.toHexString(GFG.getSHA(password));
        for (Client client : database.getClients()) {
            if (client.getUserName().equals(username) && client.getPassword().equals(hashedPass)) {
                logger.info("username "+username+"was requested for sign in and was allowed to sign in.");
                return database.getClientPage(client);

            }
        }logger.info("username "+username+"was requested for sign in but the input was wrong.");
        return null;
    }

    @Override
    public synchronized Page signUp(Client client, String id, String bio) {
        logger.info("username "+client.getUserName()+"was signed up.");
        Page page = new Page(client, id, bio, LocalDate.now());
        database.getClients().add(client);
        database.getClientIds().put(client.getUserName(), client);
        database.getAllClientPages().put(client, page);
        return page;
    }

    @Override
    public synchronized ArrayList<String> AllUserNames() {
        logger.info("all usernames were returned.");
        return database.getUserNames();

    }
}