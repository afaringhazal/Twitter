import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthenticationService {


    public Page signUp(Client clientToLogin,String bio,String id) throws NoSuchAlgorithmException {
        for (Client client : Server.getClients()) {
            if (client.getUserName().equals(clientToLogin.getUserName())) {
                // System.out.println("There is already an account associated with this username.");
                return null;
            }
        }


        Page page = new Page(clientToLogin, id, bio, LocalDate.now());
        Server.addClient(page, clientToLogin);

        return page;
    }


    public Page signIn(String username, String password) throws NoSuchAlgorithmException {
        //String password;
        for (Client client : Server.getClients()) {
            if (client.getUserName().equals(username) && client.getPassword().equals(password)) {
                return Server.getClientPage(client);
            }
        }

        return null;
    }


}