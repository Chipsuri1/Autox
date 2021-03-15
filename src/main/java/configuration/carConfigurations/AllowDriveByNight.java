package configuration.carConfigurations;

public class AllowDriveByNight extends CarConfiguration{

    public AllowDriveByNight(Integer id, String name, String value) {
        super(id, name, value);

        getAllowedValues().add("true");
        getAllowedValues().add("false");
    }
}
