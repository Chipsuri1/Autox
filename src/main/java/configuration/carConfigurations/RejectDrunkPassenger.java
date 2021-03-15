package configuration.carConfigurations;

public class RejectDrunkPassenger extends CarConfiguration{

    public RejectDrunkPassenger(Integer id, String name, String value) {
        super(id, name, value);

        getAllowedValues().add("true");
        getAllowedValues().add("false");
    }
}
