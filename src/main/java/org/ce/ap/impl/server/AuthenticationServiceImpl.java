package main.java.org.ce.ap.impl.server;

import main.java.org.ce.ap.server.*;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AuthenticationServiceImpl implements AuthenticationService {


    Database database;


    public AuthenticationServiceImpl(Database database) throws NoSuchAlgorithmException {
        this.database = database;


    }

    @Override
    public PersonalInformation signUpRequest(String userName, String password) throws NoSuchAlgorithmException {
        for (Client client : database.getClients()) {
            if (client.getUserName().equals(userName)) {
                return null;
            }
        }

        return new PersonalInformation(userName, password);
    }

    @Override
    public Page signInRequest(String username, String password) throws NoSuchAlgorithmException {
        String hashedPass = GFG.toHexString(GFG.getSHA(password));
        for (Client client : database.getClients()) {
            if (client.getUserName().equals(username) && client.getPassword().equals(hashedPass)) {
                return database.getClientPage(client);
            }
        }
        return null;
    }

    @Override
    public Page signUp(Client client, String id, String bio) {
        Page page = new Page(client, id, bio, LocalDate.now());
        database.getClients().add(client);
        database.getClientIds().put(client.getUserName(), client);
        database.getAllClientPages().put(client, page);
        return page;
    }

    @Override
    public ArrayList<String> AllUserNames() {
        return database.getUserNames();

    }
}