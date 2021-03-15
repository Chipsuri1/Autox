package event.gps;

public class GPSConnectSatellite {

    private final String frequency;

    public GPSConnectSatellite(String frequency){
        this.frequency = frequency;
    }

    public String toString() {
        return "Event: GPS - ConnectSatellite(" + frequency + ")";
    }

    public String getFrequency() {
        return frequency;
    }
}
