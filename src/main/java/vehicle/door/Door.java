package vehicle.door;

import vehicle.sensors.DoorSensor;

public class Door {
    private IDoorState state = new Closed();
    private final DoorSensor sensor;
    private final ElectricEngineDoor electricEngineDoor;

    public Door (){
        electricEngineDoor = new ElectricEngineDoor();
        sensor = new DoorSensor(electricEngineDoor, this);
    }

    public DoorSensor getSensor() {
        return sensor;
    }


    public void setState(IDoorState state) {
        this.state = state;
    }

    public void close(){
        this.state = new Closed();
    }

    public void open(){
        this.state = new Open();
    }

    public IDoorState getState() {
        return state;
    }
}
