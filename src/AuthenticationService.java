import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import java.util.InputMismatchException;
import java.util.Scanner;


public class AuthenticationService {


    /**signup
     * @param username signup username
     * @param password signup password
     * @return returns created page
     * @throws NoSuchAlgorithmException
     */
    public Page signUp(String username, String password) throws NoSuchAlgorithmException {
        for (Client client : Server.getClients()) {
            if (client.getUserName().equals(username)) {
                System.out.println("There is already an account associated with this username.");
                return Server.getClientPage(client);
            }
        }


        String firstname, lastName;
        LocalDate birthday;
        int year, month, day;
        Scanner scanner = new Scanner(System.in);
        firstname = scanner.nextLine();
        lastName = scanner.nextLine();

        while (true) {
            try {
                year = scanner.nextInt();
                month = scanner.nextInt();
                day = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input!!!");
            } finally {
                scanner.nextLine();
            }

        }

        birthday = LocalDate.of(year, month, day);

        Client client = new Client(firstname, lastName, birthday, username, password);
        Page page;

        while (true) {
            String bio = scanner.nextLine();
            String id = scanner.nextLine();

            try {
                page = new Page(client, id, bio, LocalDate.now());
                break;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }


        }


        Server.addClient(page, client);

        return page;
    }


    /** sign in
     * @param username sign in username
     * @return returns the page created
     * @throws NoSuchAlgorithmException
     */
    public Page signIn(String username) throws NoSuchAlgorithmException {
        String password;
        for (Client client : Server.getClients()) {
            if (client.getUserName().equals(username)) {
                int choice = 1;
                while (choice == 1) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter Password:");
                    password = GFG.toHexString(GFG.getSHA(scanner.nextLine()));
                    if (client.getPassword().equals(password)) {

                        return Server.getClientPage(client);

                    } else {
                        System.out.println("Incorrect password.");
                        while (true) {
                            try {
                                System.out.println("1:Try again\n2:Exit");
                                choice = scanner.nextInt();
                                if (choice != 1 && choice != 2)
                                    throw new RuntimeException("Incorrect!");
                                break;
                            } catch (InputMismatchException io) {
                                scanner.nextLine();
                                System.out.println("Invalid input.");

                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());

                            }


                        }

                    }
                }
                return null;
            } else {
                System.out.println("There is no account associated with this username.");
            }
        }
        return null;
    }

}
