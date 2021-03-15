package vehicle.door;

public class Open implements IDoorState{

    public void switchState(Door door) {
        System.out.println("closed -> open");
        door.setState(new Open());
    }
}
