package org.ce.ap.client;
import org.ce.ap.Response;
import java.util.ArrayList;

/**
 * The interface Console view service.
 */
public interface ConsoleViewService {

    /**
     * Print all tweets.
     *
     * @param response the response
     */
     void printAllTweets(Response response) ;
     /**
     * Print list.
     *
     * @param response the response
     */
     void printList(Response response) ;

    /**
     * Print followers and followings.
     *
     * @param response the response
     */
     void printFollowersAndFollowings(Response response) ;


}
