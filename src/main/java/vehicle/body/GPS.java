package vehicle.body;

public class GPS {
    public void on(){
        System.out.println("gps on");
    }

    public void off(){
        System.out.println("gps off");
    }

    public void connectSatellite(String frequency){
        System.out.println("Gps set to " + frequency);
    }
}
