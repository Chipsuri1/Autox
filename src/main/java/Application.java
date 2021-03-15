import base.chargingStation.AutoXAdapter;
import configuration.Configuration;
import configuration.ConfigurationOption;
import vehicle.Vehicle;
import vehicle.base.CentralControlUnit;
import vehicle.door.Door;
import vehicle.sensors.TemperatureSensor;
import vehicle.sensors.UltrasonicSensor;

public class Application {
    public static void main(String[] args) {
        Application application = new Application();


        if (Configuration.instance.parameter.equals("-config")) {
            new ConfigurationOption().showOptions();
        }
        application.start();
    }

    private void start() {
        Vehicle autoX = buildAutoX();
        chargeAutoX();
        facade(autoX);
        commandDoors(autoX);
        observe(autoX);
        state();
        System.out.printf("");
    }

    public Vehicle buildAutoX() {
        Vehicle autoX = new Vehicle.Builder().setChassis().setEngine()
                .setLed(2)
                .setBrakeLights(2)
                .setIndicators(4)
                .setDoors(4)
                .setSeats(6)
                .setTires(4)
                .setBrakes(8)
                .setGPS(2)
                .setCameras(2)
                .setLidar(4)
                .build();

        return autoX;
    }

    public void chargeAutoX() {
        AutoXAdapter autoX = new AutoXAdapter();
        autoX.plugAdapterAutoX();
    }

    public void facade(Vehicle autoX) {
        autoX.startup();
        autoX.move(100, 10);
        autoX.leftTurn(10, 10);
        autoX.rightTurn(10, 10);
        autoX.stop();
        autoX.emergencyStop();
        autoX.shutdown();
    }

    public void commandDoors(Vehicle autoX) {
        CentralControlUnit centralControlUnit = new CentralControlUnit(autoX);
        centralControlUnit.decryptSignal(true);
    }

    public void observe(Vehicle autoX) {
        System.out.println();
        CentralControlUnit centralControlUnit = new CentralControlUnit(autoX);

        UltrasonicSensor ultrasonicSensor = new UltrasonicSensor();
        TemperatureSensor temperatureSensor = new TemperatureSensor();

        centralControlUnit.addListener(ultrasonicSensor);
        centralControlUnit.addListener(temperatureSensor);
        for (Door door : autoX.getDoors()) {
            centralControlUnit.addListener(door.getSensor());
        }

        centralControlUnit.triggerSensorDoor();
        centralControlUnit.triggerSensorUltrasonic();
        centralControlUnit.triggerSensorTemperature();
    }

    public void state() {
        System.out.println();
        Door door = new Door();
        System.out.println("Initial: " + door.getState());

        door.open();
        System.out.println("Opened: " + door.getState());

        door.close();
        System.out.println("Closed: " + door.getState());
    }
}