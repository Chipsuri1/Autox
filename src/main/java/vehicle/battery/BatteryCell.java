package vehicle.battery;

import java.util.ArrayList;
import java.util.List;

public class BatteryCell extends Cell {

    private int energy;

    private final List<Cell> cellList = new ArrayList<>();

    public void add(Cell cell) {
        cellList.add(cell);
    }

    public void remove(Cell cell) {
        cellList.remove(cell);
    }

    public Cell getChild(int i) {
        System.out.println("error");
        return null;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }
}
