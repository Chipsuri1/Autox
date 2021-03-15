package vehicle.engine;

import vehicle.battery.BatteryCell;

public class EngineNG extends Engine {

    public void on() {
        System.out.println("EngineNG is turned on");
    }

    public void off() {
        System.out.println("EngineNG is turned off");
    }

    public void useEnergyUnitPerIteration(BatteryCell[] batteryCells) {
        for (BatteryCell batteryCell : batteryCells) {
            useEnergyUnit(batteryCell);
        }
        System.out.println("uses 3 energyUnits per Iteration");
    }
}
