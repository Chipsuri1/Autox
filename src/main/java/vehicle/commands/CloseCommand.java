package vehicle.commands;

import vehicle.door.Door;

public class CloseCommand implements ICommand {
    private final Door door;

    public CloseCommand(Door door){
        this.door = door;
    }

    public void execute(){
        door.close();
        System.out.println("Door closed");
    }
}
