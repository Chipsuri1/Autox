package vehicle.battery;

import java.util.ArrayList;
import java.util.List;

public class MainCell extends Cell{

    private final List<Cell> cellList = new ArrayList<>();

    public void add(Cell cell) {
        cellList.add(cell);
    }

    public void remove(Cell cell) {
        cellList.remove(cell);
    }

    public Cell getChild(int i) {
        return cellList.get(i);
    }

    public List<Cell> getCellList() {
        return cellList;
    }
}
