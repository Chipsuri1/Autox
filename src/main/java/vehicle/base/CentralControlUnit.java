package vehicle.base;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
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
import org.checkerframework.checker.units.qual.A;
import vehicle.Vehicle;
import vehicle.door.Door;
import vehicle.security.AES;
import vehicle.security.ElectricKey;
import vehicle.sensors.DoorSensor;
import vehicle.sensors.IDoorSensorListener;
import vehicle.sensors.ITemperatureSensorListener;
import vehicle.sensors.IUltrasonicSensorListener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class CentralControlUnit {

    private final EventBus eventBus;
    private final Vehicle vehicle;
    private final ElectricSignalReceiver electricSignalReceiver;

    private final ArrayList<ITemperatureSensorListener> temperatureSensorListenerList;
    private final ArrayList<IUltrasonicSensorListener> ultrasonicSensorListeners;
    private final ArrayList<IDoorSensorListener> doorSensorListeners;

    public CentralControlUnit(Vehicle vehicle) {
        eventBus = new EventBus("Vehicle");
        eventBus.register(this);

        temperatureSensorListenerList = new ArrayList<>();
        ultrasonicSensorListeners = new ArrayList<>();
        doorSensorListeners = new ArrayList<>();

        electricSignalReceiver = new ElectricSignalReceiver(new ElectricKey());

        this.vehicle = vehicle;
    }

    public void decryptSignal(boolean activate) {
        String decrypted = AES.decrypt(electricSignalReceiver.getSignal(), Configuration.instance.secretKey);

        if (decrypted.equals("AutoX23")) {
            if (activate) {
                vehicle.activate();
                for (Door door : vehicle.getDoors()) {
                    door.getSensor().open();
                }
            } else {
                vehicle.deactivate();
                for (Door door : vehicle.getDoors()) {
                    door.getSensor().close();
                }
            }
        } else {
            System.out.println("Key invalid");
        }
    }

    public void post(Object object) {
        eventBus.post(object);
    }

    @Subscribe
    public void receive(BrakeSet event) {
        System.out.println(event);

        Arrays.stream(vehicle.getBrakes()).forEach(brake -> brake.setBrake(event.getPercentage()));
    }

    @Subscribe
    public void receive(BrakeLightOff event) {
        System.out.println(event);

        Arrays.stream(vehicle.getBrakeLights()).forEach(brakeLight -> brakeLight.off());
    }

    @Subscribe
    public void receive(BrakeLightOn event) {
        System.out.println(event);

        Arrays.stream(vehicle.getBrakeLights()).forEach(brakeLight -> brakeLight.on());
    }

    @Subscribe
    public void receive(CameraOn event) {
        try {
            System.out.println(event);

            for (int i = 0; i < vehicle.getCameras().length; i++) {
                Method method = vehicle.getCameras()[i].getClass().getDeclaredMethod("on");
                boolean isOn = (boolean) method.invoke(vehicle.getCameras()[i]);
                System.out.println("camera turned on " + isOn);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(CameraOff event) {
        try {
            System.out.println(event);

            for (int i = 0; i < vehicle.getCameras().length; i++) {
                Method method = vehicle.getCameras()[i].getClass().getDeclaredMethod("off");
                boolean isOn = (boolean) method.invoke(vehicle.getCameras()[i]);
                System.out.println("camera turned on " + isOn);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(DecreaseRPM event) {
        System.out.println(event);

        vehicle.getEngine().decreaseRPM(event.getDeltaRPM(), event.getSeconds());
    }

    @Subscribe
    public void receive(EngineOn event) {
        System.out.println(event);

        vehicle.getEngine().on();
    }

    @Subscribe
    public void receive(EngineOff event) {
        System.out.println(event);

        vehicle.getEngine().off();
    }

    @Subscribe
    public void receive(IncreaseRPM event) {
        System.out.println(event);

        vehicle.getEngine().increaseRPM(event.getDeltaRPM(), event.getSeconds());
    }

    @Subscribe
    public void receive(GPSOff event) {
        System.out.println(event);

        Arrays.stream(vehicle.getGps()).forEach(gps -> gps.off());
    }

    @Subscribe
    public void receive(GPSOn event) {
        System.out.println(event);

        Arrays.stream(vehicle.getGps()).forEach(gps -> gps.on());
    }

    @Subscribe
    public void receive(GPSConnectSatellite event) {
        System.out.println(event);

        Arrays.stream(vehicle.getGps()).forEach(gps -> gps.connectSatellite(event.getFrequency()));
    }

    @Subscribe
    public void receive(HazardWarningOff event) {
        System.out.println(event);

        Arrays.stream(vehicle.getIndicators()).forEach(indicator -> indicator.hazardWarningOff());
    }

    @Subscribe
    public void receive(HazardWarningOn event) {
        System.out.println(event);

        Arrays.stream(vehicle.getIndicators()).forEach(indicator -> indicator.hazardWarningOn());
    }

    @Subscribe
    public void receive(LeftIndicatorOff event) {
        System.out.println(event);

        Arrays.stream(vehicle.getIndicators()).forEach(indicator -> indicator.leftIndicatorOff());
    }

    @Subscribe
    public void receive(LeftIndicatorOn event) {
        System.out.println(event);

        Arrays.stream(vehicle.getIndicators()).forEach(indicator -> indicator.leftIndicatorOn());
    }

    @Subscribe
    public void receive(RightIndicatorOff event) {
        System.out.println(event);

        Arrays.stream(vehicle.getIndicators()).forEach(indicator -> indicator.rightIndicatorOff());
    }

    @Subscribe
    public void receive(RightIndicatorOn event) {
        System.out.println(event);

        Arrays.stream(vehicle.getIndicators()).forEach(indicator -> indicator.rightIndicatorOn());
    }

    @Subscribe
    public void receive(LedDimmed event) {
        System.out.println(event);

        Arrays.stream(vehicle.getLed()).forEach(led -> led.dimmed());
    }

    @Subscribe
    public void receive(LedHighBeam event) {
        System.out.println(event);

        Arrays.stream(vehicle.getLed()).forEach(led -> led.highBeam());
    }

    @Subscribe
    public void receive(LedOff event) {
        System.out.println(event);

        Arrays.stream(vehicle.getLed()).forEach(led -> led.off());
    }

    @Subscribe
    public void receive(LedOn event) {
        System.out.println(event);

        Arrays.stream(vehicle.getLed()).forEach(led -> led.on());
    }

    @Subscribe
    public void receive(LidarOff event) {
        System.out.println(event);

        Arrays.stream(vehicle.getLidar()).forEach(lidar -> lidar.off());
    }

    @Subscribe
    public void receive(LidarOn event) {
        System.out.println(event);

        Arrays.stream(vehicle.getLidar()).forEach(lidar -> lidar.on());
    }


    public void triggerSensorTemperature() {
        for (ITemperatureSensorListener listener : temperatureSensorListenerList) {
            listener.updateTemperature("30°C");
        }
    }

    public void triggerSensorDoor() {
        for (IDoorSensorListener listener : doorSensorListeners) {
            listener.updateDoorStatus("closed");
        }
    }

    public void triggerSensorUltrasonic() {
        for (IUltrasonicSensorListener listener : ultrasonicSensorListeners) {
            listener.updateDistance("50 m");
        }
    }

    public void addListener(IDoorSensorListener listener) {
        doorSensorListeners.add(listener);
    }

    public void addListener(ITemperatureSensorListener listener) {
        temperatureSensorListenerList.add(listener);
    }

    public void removeListener(ITemperatureSensorListener listener) {
        temperatureSensorListenerList.add(listener);
    }

    public void addListener(IUltrasonicSensorListener listener) {
        ultrasonicSensorListeners.add(listener);
    }

    public void removeListener(IUltrasonicSensorListener listener) {
        ultrasonicSensorListeners.remove(listener);
    }


}
