package org.ce.ap.impl.client;
import com.google.gson.internal.LinkedTreeMap;
import org.ce.ap.Response;
import org.ce.ap.client.ConsoleViewService;
import java.util.ArrayList;
 /**
 * class : type Console view service.
 * @author MohammadHadi sheikheslami
 * @author Rezvan Afari
 * @version 1.0.0
 */

public class ConsoleViewServiceImpl implements ConsoleViewService {
     /**
      * Print all tweets.
      *
      * @param response the response
      */
     @Override
     public void printAllTweets(Response response) {
         for (Object obj : response.getResults()) {
             LinkedTreeMap<String, Object> treeMap = (LinkedTreeMap<String, Object>) obj;
             System.out.println("|" + treeMap.get("clientUsername") + "          id : " + treeMap.get("id") + "          " + treeMap.get("date"));
             for (String s : treeMap.keySet()) {
                 if (s.equals("tweet")) {

                     System.out.println("|_______________________");
                     LinkedTreeMap<String, Object> treeMapToRetweet = new LinkedTreeMap<>();
                     treeMapToRetweet = (LinkedTreeMap<String, Object>) treeMap.get("tweet");
                     System.out.println("|---|" + treeMapToRetweet.get("clientUsername") + "          id : " + treeMapToRetweet.get("id") + "          " + treeMapToRetweet.get("date"));
                     System.out.println("|---|" + treeMapToRetweet.get("text"));
                     System.out.println("|---|" + ((ArrayList<Object>) treeMapToRetweet.get("retweets")).size() + " Retweets, " + ((ArrayList<String>) treeMapToRetweet.get("likes")).size() + " Likes");

                     System.out.println("|Like : ");
                     for (int i = 0; i < ((ArrayList<Object>) treeMapToRetweet.get("likes")).size(); i++) {
                         System.out.print("|---|");
                         System.out.println(((ArrayList<Object>) treeMapToRetweet.get("likes")).get(i));
                     }
                     System.out.println("|Retweet :");

                     for (int i = 0; i < ((ArrayList<Object>) treeMapToRetweet.get("retweets")).size(); i++) {
                         // initLine(degree+1);
                         System.out.print("|---|");
                         System.out.println(((ArrayList<Object>) treeMapToRetweet.get("retweets")).get(i));
                     }


                     System.out.println("|-----------------------");
                 }

             }


             System.out.println("|" + treeMap.get("text"));
             System.out.println("|" + ((ArrayList<Object>) treeMap.get("retweets")).size() + " Retweets, " + ((ArrayList<String>) treeMap.get("likes")).size() + " Likes");
             // printReply((ArrayList<Object>) treeMap.get("replies"), 0);
             System.out.println("|Like : ");
             for (int i = 0; i < ((ArrayList<Object>) treeMap.get("likes")).size(); i++) {
                 System.out.print("|---|");
                 System.out.println(((ArrayList<Object>) treeMap.get("likes")).get(i));
             }
             System.out.println("|Retweet :");

             for (int i = 0; i < ((ArrayList<Object>) treeMap.get("retweets")).size(); i++) {
                 // initLine(degree+1);
                 System.out.print("|---|");
                 System.out.println(((ArrayList<Object>) treeMap.get("retweets")).get(i));
             }


             System.out.println("\n");
         }

     }

     /**
      * Print list.
      *
      * @param response the response
      */
     @Override
     public void printList(Response response) {

         for (Object obj : response.getResults()) {
             System.out.println((String) obj);
         }
     }

     /**
      * Print followers and followings.
      *
      * @param response the response
      */
     @Override
     public void printFollowersAndFollowings(Response response) {

         ArrayList<Object> result = response.getResults();
         ArrayList<Object> followers = (ArrayList<Object>) result.get(0);
         ArrayList<Object> followings = (ArrayList<Object>) result.get(1);

         System.out.println("Followers : ");
         for (Object userNameFollower : followers) {
             System.out.println((String) userNameFollower);

         }
         System.out.println();
         System.out.println("Followings : ");
         for (Object userNameFollowing : followings) {
             System.out.println((String) userNameFollowing);
         }

     }


 }
