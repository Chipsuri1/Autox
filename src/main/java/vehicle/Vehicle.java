package vehicle;

import configuration.Configuration;
import event.brake.BrakeSet;
import event.brakeLights.BrakeLightOff;
import event.brakeLights.BrakeLightOn;
import event.camera.CameraOff;
import event.camera.CameraOn;
import event.engine.DecreaseRPM;
import event.engine.EngineOff;
import event.engine.EngineOn;
import event.engine.IncreaseRPM;
import event.gps.GPSConnectSatellite;
import event.gps.GPSOff;
import event.gps.GPSOn;
import event.indicator.*;
import event.led.LedDimmed;
import event.led.LedHighBeam;
import event.led.LedOff;
import event.led.LedOn;
import event.lidar.LidarOff;
import event.lidar.LidarOn;
import factory.CameraV1Factory;
import vehicle.base.CentralControlUnit;
import vehicle.battery.Battery;
import vehicle.battery.BatteryCell;
import vehicle.battery.MainCell;
import vehicle.battery.SubCell;
import vehicle.body.*;
import vehicle.door.Door;
import vehicle.engine.Engine;

import java.util.ArrayList;
import java.util.List;

public class Vehicle implements IVehicle {

    private CentralControlUnit centralControlUnit;
    private boolean activated;

    private Engine engine;
    private Chassis chassis;
    private LED[] led;
    private BrakeLight[] brakeLights;
    private Indicator[] indicators;
    private Door[] doors;
    private Seat[] seats;
    private Tire[] tires;
    private Brake[] brakes;
    private GPS[] gps;
    private Lidar[] lidar;
    private Object[] cameras;
    private final Battery[] batteries = new Battery[2];

    private Vehicle(Builder builder) {
        centralControlUnit = new CentralControlUnit(this);

        engine = builder.engine;
        chassis = builder.chassis;
        led = builder.led;
        brakeLights = builder.brakeLights;
        indicators = builder.indicators;
        doors = builder.doors;
        seats = builder.seats;
        tires = builder.tires;
        brakes = builder.brakes;
        gps = builder.gps;
        lidar = builder.lidar;
        cameras = builder.cameras;

        setBatteries();
    }

    public void activate() {
        activated = true;
        System.out.println("Car activated!");
    }

    public void deactivate() {
        activated = false;
        System.out.println("Car deactivated!");
    }

    public void startup() {
        System.out.println("startup");
        centralControlUnit.post(new EngineOn());
        centralControlUnit.post(new LedOn());
        centralControlUnit.post(new GPSOn());
        centralControlUnit.post(new GPSConnectSatellite("118.75"));
        centralControlUnit.post(new CameraOn());
        centralControlUnit.post(new LidarOn());
        centralControlUnit.post(new LeftIndicatorOff());
        System.out.println();
    }

    public void move(int deltaRPM, int seconds) {
        System.out.println("move " + "(" + deltaRPM + ", " + seconds + ")");
        centralControlUnit.post(new LeftIndicatorOff());
        centralControlUnit.post(new RightIndicatorOff());
        centralControlUnit.post(new LedDimmed());
        centralControlUnit.post(new IncreaseRPM(deltaRPM, seconds));
        centralControlUnit.post(new BrakeSet(0));
        centralControlUnit.post(new BrakeLightOff());
        System.out.println();
    }

    public void leftTurn(int deltaRPM, int seconds) {
        System.out.println("left " + "(" + deltaRPM + ", " + seconds + ")");
        centralControlUnit.post(new LeftIndicatorOn());
        centralControlUnit.post(new DecreaseRPM(deltaRPM, seconds));
        centralControlUnit.post(new BrakeSet(70));
        centralControlUnit.post(new BrakeLightOn());
        System.out.println();
    }

    public void rightTurn(int deltaRPM, int seconds) {
        System.out.println("right " + "(" + deltaRPM + ", " + seconds + ")");
        centralControlUnit.post(new RightIndicatorOn());
        centralControlUnit.post(new DecreaseRPM(deltaRPM, seconds));
        centralControlUnit.post(new BrakeSet(70));
        centralControlUnit.post(new BrakeLightOn());
        System.out.println();
    }

    public void stop() {
        System.out.println("stop");
        centralControlUnit.post(new BrakeSet(100));
        centralControlUnit.post(new BrakeLightOn());
        System.out.println();
    }

    public void emergencyStop() {
        System.out.println("emergencyStop");
        centralControlUnit.post(new BrakeSet(100));
        centralControlUnit.post(new BrakeLightOn());
        centralControlUnit.post(new HazardWarningOn());
        centralControlUnit.post(new EngineOff());
        centralControlUnit.post(new LedHighBeam());
        centralControlUnit.post(new CameraOff());
        centralControlUnit.post(new LidarOff());
        System.out.println();
    }

    public void shutdown() {
        System.out.println("shutdown");
        centralControlUnit.post(new BrakeSet(100));
        centralControlUnit.post(new EngineOff());
        centralControlUnit.post(new BrakeLightOff());
        centralControlUnit.post(new LedOff());
        centralControlUnit.post(new HazardWarningOff());
        centralControlUnit.post(new GPSOff());
        centralControlUnit.post(new CameraOff());
        centralControlUnit.post(new LidarOff());
        System.out.println();
    }

    public static class Builder {
        private Engine engine;
        private Chassis chassis;
        private LED[] led;
        private BrakeLight[] brakeLights;
        private Indicator[] indicators;
        private Door[] doors;
        private Seat[] seats;
        private Tire[] tires;
        private Brake[] brakes;
        private GPS[] gps;
        private Lidar[] lidar;
        private Object[] cameras;

        public Builder() {
        }

        public Builder setChassis() {
            chassis = new Chassis();
            return this;
        }

        public Builder setEngine() {
            engine = Configuration.instance.engine;
            return this;
        }

        public Builder setLed(int numberOfLED) {
            led = new LED[numberOfLED];

            for (int i = 0; i < numberOfLED; i++) {
                led[i] = new LED();
            }
            return this;
        }

        public Builder setBrakes(int numberOfBrakes) {
            brakes = new Brake[numberOfBrakes];

            for (int i = 0; i < numberOfBrakes; i++) {
                brakes[i] = new Brake();
            }
            return this;
        }

        public Builder setBrakeLights(int numberOfBrakeLights) {
            brakeLights = new BrakeLight[numberOfBrakeLights];

            for (int i = 0; i < numberOfBrakeLights; i++) {
                brakeLights[i] = new BrakeLight();
            }
            return this;
        }

        public Builder setIndicators(int numberOfIndicators) {
            indicators = new Indicator[numberOfIndicators];

            for (int i = 0; i < numberOfIndicators; i++) {
                indicators[i] = new Indicator();
            }
            return this;
        }

        public Builder setDoors(int numberOfDoors) {
            doors = new Door[numberOfDoors];

            for (int i = 0; i < numberOfDoors; i++) {
                doors[i] = new Door();
            }
            return this;
        }

        public Builder setSeats(int numberOfSeats) {
            seats = new Seat[numberOfSeats];

            for (int i = 0; i < numberOfSeats; i++) {
                seats[i] = new Seat();
            }
            return this;
        }

        public Builder setTires(int numberOfTires) {
            tires = new Tire[numberOfTires];

            for (int i = 0; i < numberOfTires; i++) {
                tires[i] = new Tire();
            }
            return this;
        }

        public Builder setGPS(int numberOfGPS) {
            gps = new GPS[numberOfGPS];

            for (int i = 0; i < numberOfGPS; i++) {
                gps[i] = new GPS();
            }
            return this;
        }

        public Builder setCameras(int numberOfCameras) {
            List<Object> cameraList = new ArrayList<>();

            for (int i = 0; i < numberOfCameras; i++) {
                cameraList.add(Configuration.instance.cameraV1Factory.build());
            }
            cameras = cameraList.toArray();

            return this;
        }

        public Builder setLidar(int numberOfLidar) {
            lidar = new Lidar[numberOfLidar];

            for (int i = 0; i < numberOfLidar; i++) {
                lidar[i] = Configuration.instance.lidar;
            }
            return this;
        }


        public Vehicle build() {
            return new Vehicle(this);
        }

    }

    public void setBatteries() {
        for (int b = 0; b < batteries.length; b++) {
            Battery battery = new Battery();
            batteries[b] = battery;

            for (int i = 0; i < 32; i++) {
                MainCell mainCell = new MainCell();
                battery.add(mainCell);

                for (int j = 0; j < 8; j++) {
                    SubCell subCell = new SubCell();
                    mainCell.add(subCell);

                    for (int k = 0; k < 2; k++) {
                        BatteryCell batteryCell = new BatteryCell();
                        subCell.add(batteryCell);
                    }
                }
            }
        }
    }

    @Override
    public void plugInAdapter() {
        System.out.println("autoX charges");
    }

    public Brake[] getBrakes() {
        return brakes;
    }

    public Engine getEngine() {
        return engine;
    }

    public Chassis getChassis() {
        return chassis;
    }

    public LED[] getLed() {
        return led;
    }

    public BrakeLight[] getBrakeLights() {
        return brakeLights;
    }

    public Indicator[] getIndicators() {
        return indicators;
    }

    public Door[] getDoors() {
        return doors;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public Tire[] getTires() {
        return tires;
    }

    public GPS[] getGps() {
        return gps;
    }

    public Lidar[] getLidar() {
        return lidar;
    }

    public Object[] getCameras() {
        return cameras;
    }
}
