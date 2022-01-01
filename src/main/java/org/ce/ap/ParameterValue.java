package main.java.org.ce.ap;

/**
 * The type Parameter value.
 */
public class ParameterValue {
    private String name;
    private Object value;

    /**
     * Instantiates a new Parameter value.
     *
     * @param name  the name
     * @param value the value
     */
    public ParameterValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
