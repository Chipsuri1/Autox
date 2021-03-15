package vehicle.sensors;

import java.util.ArrayList;

public class UltrasonicSensor implements IUltrasonicSensorListener {

    public void updateDistance(String distance) {
        observeDistance(distance);
    }

    private void observeDistance(String distance){
        System.out.println("Current distance: " + distance);
    }
}
