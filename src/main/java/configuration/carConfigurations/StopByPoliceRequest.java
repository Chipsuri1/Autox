package configuration.carConfigurations;

public class StopByPoliceRequest extends CarConfiguration{

    public StopByPoliceRequest(Integer id, String name, String value) {
        super(id, name, value);

        getAllowedValues().add("true");
        getAllowedValues().add("false");
    }
}
