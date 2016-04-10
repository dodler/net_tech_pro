package lian.artyom.dao.pojo;

/**
 * simple POJO object for keeping some extra parameters of
 * tasks
 * it's mapping is set in resources/mapping/param.hbm.xml file
 * Created by dodler on 27/02/16.
 */
public class Parameter {


    private int id;
    private String value, key;

    public Parameter() {
        this.value = key = "";
    }

    public Parameter(String value, String key) {
        this.value = value;
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
