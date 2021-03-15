package configuration.carConfigurations;

public class MusicDuringDrive extends CarConfiguration{

    public MusicDuringDrive(Integer id, String name, String value) {
        super(id, name, value);

        getAllowedValues().add("ac/dc");
        getAllowedValues().add("helene fischer");
    }
}
