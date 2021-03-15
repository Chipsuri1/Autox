package vehicle.engine;

import vehicle.battery.BatteryCell;

public class EngineX extends Engine {

    public void on() {
        System.out.println("EngineX is turned on");
    }

    public void off() {
        System.out.println("EngineX is turned off");
    }

    public void useEnergyUnitPerIteration(BatteryCell[] batteryCells) {
        for (BatteryCell batteryCell : batteryCells) {
            useEnergyUnit(batteryCell);
        }
        System.out.println("uses 4 energyUnits per Iteration");
    }
}
