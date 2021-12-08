import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthenticationService {
   // Server server;

//    public AuthenticationService(Server server) {
//        this.server = server;
//
//    }


    public Page signUp(String username, String password) throws NoSuchAlgorithmException
    {
        for (Client client : Server.getClients()) {
            if (client.getUserName().equals(username) && client.getPassword().equals(password))
            {
                System.out.println("There is already an account associated with this username.");
                return Server.getBasic(client);
            }
        }


        String firstname, lastName;
        LocalDate birthday;
        int year, month  , day;
        Scanner scanner = new Scanner(System.in);
        firstname =scanner.nextLine();
        lastName = scanner.nextLine();

        year = scanner.nextInt();
        month = scanner.nextInt();
        day = scanner.nextInt();


//        while (true)
//        {
//            try{
//                year =scanner.nextInt();
//                month = scanner.nextInt();
//                day = scanner.nextInt();
//                break;
//            }
//            catch (Exception e)
//            {
//                System.out.println("Invalid input!!!");
//            }
//
//        }

        birthday = LocalDate.of(year,month,day);

        Client client = new Client(firstname,lastName,birthday,username,password);
        Page page ;


        String bio = scanner.nextLine();
        String id =scanner.nextLine();


        page =new Page(client,id,bio,LocalDate.now());





//        while (true)
//        {
//            String bio = scanner.nextLine();
//            String id =scanner.nextLine();
//
//            try
//            {
//                page = new Page(client,id,bio,LocalDate.now());
//                break;
//            }
//            catch (RuntimeException e)
//            {
//                System.out.println(e.getMessage());
//
//
//            }
//
//        }



        Server.addClient(page,client);

        return page;
    }










//   public void signUp(String username, String password) throws NoSuchAlgorithmException {
//        for (Client client : Server.getClients()) {
//            if (client.getUserName().equals(username)) {
//                System.out.println("There is already an account associated with this username.");
//                return;
//            }
//        }
//        Client client = new Client("firstname", "lastname", 18, "", LocalDate.now(), username, password);
//        server.CreateClient(client, new Page(client));
//
//    }







    public Page signIn(String username) throws NoSuchAlgorithmException  {
        String password;
        for (Client client : Server.getClients())
        {
            if (client.getUserName().equals(username))
            {
                int choice = 1;
                while (choice == 1)
                {
                    Scanner scanner = new Scanner(System.in)  ;
                    System.out.println("Enter Password:");
                     password = GFG.toHexString(GFG.getSHA(scanner.nextLine()));
                    if (client.getPassword().equals(password))
                    {

                        return Server.getBasic(client);

                    }
                    else
                    {
                        System.out.println("Incorrect password.");
                        System.out.println("1:Try again\n2:Exit");
                        while (true)
                        {try
                            {
                                System.out.println("1:Try again\n2:Exit");
                                choice = scanner.nextInt();
                                if(choice!=1 && choice !=2)
                                    throw new RuntimeException("Incorrect!");
                                break;
                            }
                            catch(InputMismatchException io)
                            {
                                System.out.println("Invalid input.");
                                continue;
                            }
                            catch (RuntimeException e)
                            {
                                System.out.println(e.getMessage());
                                continue;
                            }


                        }

                    }
                }
                return null;
            }
            else
            {
                System.out.println("There is no account associated with this username.");
            }
        }
        return null;
    }


















//    public Client signIn(String username) throws NoSuchAlgorithmException  {
//        for (Client client : Server.getClients()) {
//
//
//            if (client.getUserName().equals(username)) {
//                int choice = 1;
//                while (choice == 1) {
//                    Scanner scanner = new Scanner(System.in)  ;
//                    System.out.println("Enter Password:");
//                    String password = GFG.toHexString(GFG.getSHA(scanner.nextLine()));
//                    if (client.getPassword().equals(password)) {
//                        return client;
//                    } else {
//                        System.out.println("Incorrect password.");
//                        System.out.println("1:Try again\n2:Exit");
//                        try {
//                            choice = scanner.nextInt();
//                        }
//                        catch(InputMismatchException io){
//                            System.out.println("Invalid input.");
//                        }
//
//                    }
//                }
//                return null;
//            } else {
//                System.out.println("There is no account associated with this username.");
//            }
//        }
//        return null;
//    }

}


