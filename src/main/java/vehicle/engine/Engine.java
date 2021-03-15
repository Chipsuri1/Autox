package vehicle.engine;

import vehicle.battery.BatteryCell;

public abstract class Engine {

    public abstract void on();

    public abstract void off();

    public void increaseRPM(int deltaRPM, int seconds){
        System.out.println("engineRPM increased by " + (deltaRPM * seconds));
    }

    public void decreaseRPM(int deltaRPM, int seconds){
        System.out.println("engine decrease by " + (deltaRPM * seconds));
    }

    public abstract void useEnergyUnitPerIteration(BatteryCell[] batteryCells);

    public void useEnergyUnit(BatteryCell batteryCell){
        batteryCell.setEnergy(0);
        System.out.println();
    }
}
