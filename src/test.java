import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Scanner;

public class test {

    public static void main(String[]args) throws NoSuchAlgorithmException, JSONException {
        System.out.println("kbkvkh");

        LocalDateTime l = LocalDateTime.now();
        JSONObject d = new JSONObject();

        d.put("Time","ljj");



        System.out.println(d.get("Time"));

        Scanner scanner = new Scanner(System.in);
        int n=0;
        while (true)
        {
            try {
                 n =scanner.nextInt();
                break;
            }catch (Exception e)
            {
                System.out.println("Not Valid input,Enter again!");

            }
        }




        JSONObject j = new JSONObject();

        j.put("jjds", "jhhh");

        System.out.println(j.get("jjds"));





    }
}
