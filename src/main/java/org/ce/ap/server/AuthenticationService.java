package main.java.org.ce.ap.server;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class AuthenticationService {

    Database database;
    public AuthenticationService(Database database){
        this.database=database;
    }

    public Page signUpRequest(Client clientToLogin,String bio,String id)  {
        for (Client client : database.getClients()) {
            if (client.getUserName().equals(clientToLogin.getUserName())) {
                return null;
            }
        }
        return signUp(clientToLogin, id, bio);
    }


    public Page signInRequest(String username, String password) throws NoSuchAlgorithmException {
        String hashedPass= GFG.toHexString(GFG.getSHA(password));
        for (Client client : database.getClients()) {
            if (client.getUserName().equals(username) && client.getPassword().equals(hashedPass)) {
                return database.getClientPage(client);
            }
        }
        return null;
    }


    public Page signUp(Client client, String id, String bio){
        Page page= new Page(client, id, bio, LocalDate.now());
        database.getClients().add(client);
        database.getAllClientPages().put(client,page);
        return page;
    }


}