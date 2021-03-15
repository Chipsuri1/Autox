package configuration.carConfigurations;

import java.util.ArrayList;
import java.util.List;

public abstract class CarConfiguration {

    private String name;
    private String value;
    private Integer id;
    private final List<String> allowedValues;

    public CarConfiguration(Integer id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.allowedValues = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Integer getId() {
        return id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAllowedValues() {
        return allowedValues;
    }
}
