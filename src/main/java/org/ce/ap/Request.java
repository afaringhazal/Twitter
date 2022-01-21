package org.ce.ap;

import java.util.ArrayList;

/**
 * The type Request.
 */
public class Request {

    private String title;
    private ArrayList<Object> parameters ;

    /**
     * Instantiates a new Request.
     *
     * @param title          the title
     * @param parameterValue the parameter value
     */
    public Request(String title , ArrayList<Object> parameterValue)
    {
        this.title=title;
       parameters=parameterValue;

    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets parameter value.
     *
     * @return the parameter value
     */
    public ArrayList<Object> getParameterValue() {
        return parameters;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets parameter value.
     *
     * @param parameterValue the parameter value
     */
    public void setParameterValue(ArrayList<Object> parameterValue) {
        this.parameters = parameterValue;
    }
}

