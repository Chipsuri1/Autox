package event.engine;

public class IncreaseRPM {

    private final int deltaRPM;
    private final int seconds;

    public IncreaseRPM(int deltaRPM, int seconds){
        this.deltaRPM = deltaRPM;
        this.seconds = seconds;
    }

    public String toString() {
        return "Event: Engine - IncreaseRPM(" + deltaRPM + ", " + seconds + ")";
    }

    public int getDeltaRPM() {
        return deltaRPM;
    }

    public int getSeconds() {
        return seconds;
    }
}
