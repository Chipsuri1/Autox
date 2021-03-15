package vehicle.commands;

import vehicle.commands.ICommand;
import vehicle.door.Door;

public class OpenCommand implements ICommand {
    private final Door door;

    public OpenCommand(Door door){
        this.door = door;
    }

    public void execute(){
        door.open();
        System.out.println("Door opened");
    }
}
