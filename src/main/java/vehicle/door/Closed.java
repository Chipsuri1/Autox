package vehicle.door;

public class Closed implements IDoorState{

    public void switchState(Door door) {
        System.out.println("open -> closed");
        door.setState(new Closed());
    }
}
