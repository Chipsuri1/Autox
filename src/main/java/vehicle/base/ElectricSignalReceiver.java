package vehicle.base;

import vehicle.security.ElectricKey;

public class ElectricSignalReceiver {

    private final String signal;

    public ElectricSignalReceiver(ElectricKey electricKey){
        signal = electricKey.getPassword();
    }

    public String getSignal() {
        return signal;
    }
}
