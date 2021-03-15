package base.chargingStation;

public class AutoXAdapter extends ChargingStation implements IAutoXAdapter {
    public void plugAdapterAutoX() {
        System.out.print("autoX | ");
        plugAdapter();
    }
}
