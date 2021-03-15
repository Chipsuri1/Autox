package vehicle.sensors;

import vehicle.commands.CloseCommand;
import vehicle.commands.OpenCommand;
import vehicle.door.Door;
import vehicle.door.ElectricEngineDoor;

public class DoorSensor implements IDoorSensorListener {
    private final ElectricEngineDoor electricEngineDoor;
    private final Door door;

    public void updateDoorStatus(String status) {
        observeSensor(status);
    }

    public void observeSensor(String status) {
        System.out.println("Current status: " + status);
    }

    public DoorSensor(ElectricEngineDoor electricEngineDoor, Door door) {
        this.electricEngineDoor = electricEngineDoor;
        this.door = door;
    }

    public void open() {
        electricEngineDoor.setCommand(new OpenCommand(door));
        electricEngineDoor.placeCommand();
    }

    public void close() {
        electricEngineDoor.setCommand(new CloseCommand(door));
        electricEngineDoor.placeCommand();
    }
}
