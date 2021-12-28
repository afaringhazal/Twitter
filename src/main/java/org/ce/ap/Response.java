package main.java.org.ce.ap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Response {
    private boolean HasError;
    private int ErrorCode;
    private int  Count;
    private ArrayList<Object> Results;

    public Response(boolean hasError, int errorCode, int count, ArrayList<Object> results) {
        HasError = hasError;
        ErrorCode = errorCode;
        Count = count;
        Results = results;
    }


    public boolean isHasError() {
        return HasError;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public int getCount() {
        return Count;
    }

    public ArrayList<Object> getResults() {
        return Results;
    }

    public void setHasError(boolean hasError) {
        HasError = hasError;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public void setCount(int count) {
        Count = count;
    }

    public void setResults(ArrayList<Object> results) {
        Results = results;
    }





}
