package lyan.artyom.dao.pojo;

/**
 * simple POJO object for keeping some extra parameters of
 * tasks. point of extension, seems a bit weird.
 * it's mapping is set in resources/mapping/param.hbm.xml file
 * Created by dodler on 27/02/16.
 */
public class Parameter {


    private int id;
    private String value, key;

    public Parameter() {
        this.value = key = "";
    }

    /**
     * Simple constructor. Consider to change to factory.
     * @param value parameter value.
     * @param key parameter key.
     */
    public Parameter(String value, String key) {
        this.value = value;
        this.key = key;
    }

    /**
     * Simple naive getter method.
     * @return parameter value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Simple naive setter method.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Simple naive getter method.
     * @return parameter key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Simple naive setter method.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Simple naive getter method.
     * @return parameter id.
     */
    public int getId() {
        return id;
    }

    /**
     * Simple naive setter method.
     */
    public void setId(int id) {
        this.id = id;
    }
}
