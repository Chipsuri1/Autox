package configuration;

import com.sun.jdi.Value;
import configuration.carConfigurations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConfigurationOption implements IConfigurationOption {

    private final List<CarConfiguration> carConfigurations = new ArrayList<>();
    private final PreviousCarConfiguration previousCarConfiguration = new PreviousCarConfiguration();
    private Scanner scanner;

    public ConfigurationOption() {
        standardConfiguration();
    }

    public void standardConfiguration() {
        carConfigurations.add(new AllowDriveByNight(1, "allowDriveByNight", "true"));
        carConfigurations.add(new BehaviorWithNaggingPassengers(2, "behaviorWithNaggingPassengers", "stopAndWaitForExcuse"));
        carConfigurations.add(new MusicDuringDrive(3, "musicDuringDrive", "ac/dc"));
        carConfigurations.add(new RejectDrunkPassenger(4, "rejectDrunkPassenger", "true"));
        carConfigurations.add(new StopByPoliceRequest(5, "stopByPoliceRequest", "true"));

        scanner = new Scanner(System.in);
    }

    public void showOptions() {
        System.out.println("[i] print");
        System.out.println("[ii] set parameter");
        System.out.println("[iii] undo");
        System.out.println("[iv] exit");

        boolean valid = true;
        do {
            System.out.println("Please enter option");
            String input = scanner.nextLine();

            switch (input) {
                case "[i] print":
                    print(true);
                    break;
                case "[ii] set parameter":
                    setParameter();
                    break;
                case "[iii] undo":
                    undo();
                    break;
                case "[iv] exit":
                    valid = false;
                    break;
                default:
                    System.out.println("Error! Please try again");
            }

        }
        while (valid);
    }

    public void print(boolean showOptions) {
        for (CarConfiguration carConfiguration : carConfigurations) {
            System.out.println("[" + carConfiguration.getId() + "]" + carConfiguration.getName() + ": " + carConfiguration.getValue());
        }
        if (showOptions) {
            showOptions();
        }
    }

    public void setParameter() {
        print(false);

        List<Integer> allowedNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        Integer id;
        do {
            System.out.println("Please enter the id of the parameter you want to edit");
            id = Integer.valueOf(scanner.nextLine());
        }while (!allowedNumbers.contains(id));

        CarConfiguration carConfiguration = null;
        for (CarConfiguration carConfig : carConfigurations){
            if(carConfig.getId().equals(id)){
                carConfiguration = carConfig;
            }
        }

        String input;
        do {
            System.out.println("enter value for " + carConfiguration.getName() + " | current " + carConfiguration.getValue() + " | allowed " + carConfiguration.getAllowedValues() + " > ");
            input = scanner.nextLine();
        } while (!carConfiguration.getAllowedValues().contains(input));

        previousCarConfiguration.setName(carConfiguration.getName());
        previousCarConfiguration.setValue(carConfiguration.getValue());


        carConfiguration.setValue(input);
    }

    public void undo() {
        CarConfiguration carConfiguration = null;

        for (CarConfiguration carConfig : carConfigurations){
            if(carConfig.getName().equals(previousCarConfiguration.getName())){
                carConfiguration = carConfig;
            }
        }

        carConfiguration.setValue(previousCarConfiguration.getValue());
    }
}
