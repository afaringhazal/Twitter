package main.java.org.ce.ap;

import java.util.ArrayList;

public class Request {

    private String title;
    private ArrayList<Object> parameters ;

    public Request(String title , ArrayList<Object> parameterValue)
    {
        this.title=title;
       parameters=parameterValue;

    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Object> getParameterValue() {
        return parameters;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setParameterValue(ArrayList<Object> parameterValue) {
        this.parameters = parameterValue;
    }
}

