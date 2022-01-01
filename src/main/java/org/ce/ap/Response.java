package main.java.org.ce.ap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * The type Response.
 */
public class Response {
    private boolean HasError;
    private int ErrorCode;
    private int  Count;
    private ArrayList<Object> Results;

    /**
     * Instantiates a new Response.
     *
     * @param hasError  the has error
     * @param errorCode the error code
     * @param count     the count
     * @param results   the results
     */
    public Response(boolean hasError, int errorCode, int count, ArrayList<Object> results) {
        HasError = hasError;
        ErrorCode = errorCode;
        Count = count;
        Results = results;
    }


    /**
     * Is has error boolean.
     *
     * @return the boolean
     */
    public boolean isHasError() {
        return HasError;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return ErrorCode;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return Count;
    }

    /**
     * Gets results.
     *
     * @return the results
     */
    public ArrayList<Object> getResults() {
        return Results;
    }

    /**
     * Sets has error.
     *
     * @param hasError the has error
     */
    public void setHasError(boolean hasError) {
        HasError = hasError;
    }

    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        Count = count;
    }

    /**
     * Sets results.
     *
     * @param results the results
     */
    public void setResults(ArrayList<Object> results) {
        Results = results;
    }





}
