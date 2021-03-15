package event.brake;

public class BrakeSet {

    private final int percentage;

    public BrakeSet(int percentage){
        this.percentage = percentage;
    }

    public String toString() {
        return "Event: Brake - Set(" + percentage + ")";
    }

    public int getPercentage() {
        return percentage;
    }
}
