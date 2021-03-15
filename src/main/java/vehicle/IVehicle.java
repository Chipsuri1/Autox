package vehicle;

public interface IVehicle {
    void startup();

    void move(int deltaRPM, int seconds);

    void leftTurn(int deltaRP, int seconds);

    void rightTurn(int deltaRPM, int seconds);

    void stop();

    void emergencyStop();

    void shutdown();

    void plugInAdapter();
}
