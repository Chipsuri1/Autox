package event.engine;

public class DecreaseRPM {

    private final int deltaRPM;
    private final int seconds;

    public DecreaseRPM(int deltaRPM, int seconds){
        this.deltaRPM = deltaRPM;
        this.seconds = seconds;
    }

    public String toString() {
        return "Event: Engine - DecreaseRPM(" + deltaRPM + ", " + seconds + ")";
    }

    public int getDeltaRPM() {
        return deltaRPM;
    }

    public int getSeconds() {
        return seconds;
    }
}
