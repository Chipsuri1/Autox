package vehicle.door;

import vehicle.commands.ICommand;
import vehicle.sensors.IDoorSensorListener;

import java.util.ArrayList;

public class ElectricEngineDoor {
    private ICommand command;

    private ArrayList<IDoorSensorListener> doorSensorListenersList;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void placeCommand() {
        command.execute();
    }
}
