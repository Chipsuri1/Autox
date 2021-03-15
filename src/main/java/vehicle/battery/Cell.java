package vehicle.battery;

public abstract class Cell {

    public abstract void add (Cell cell);

    public abstract void remove(Cell cell);

    public abstract Cell getChild(int i);
}
