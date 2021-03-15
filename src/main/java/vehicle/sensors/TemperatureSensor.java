package vehicle.sensors;

public class TemperatureSensor implements ITemperatureSensorListener{

    public void updateTemperature(String temperature) {
        observeTemperature(temperature);
    }

    private void observeTemperature(String temperature){
        System.out.println("Current temperature: " + temperature);
    }
}
