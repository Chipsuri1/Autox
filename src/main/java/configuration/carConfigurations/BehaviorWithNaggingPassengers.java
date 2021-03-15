package configuration.carConfigurations;

public class BehaviorWithNaggingPassengers extends CarConfiguration{

    public BehaviorWithNaggingPassengers(Integer id, String name, String value) {
        super(id, name, value);

        getAllowedValues().add("doNothing");
        getAllowedValues().add("stopAndWaitForExcuse");
    }
}
