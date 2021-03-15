package vehicle.security;

import configuration.Configuration;

public class ElectricKey {
    private final String password;

    public ElectricKey(){
        password = AES.encrypt("AutoX23", Configuration.instance.secretKey);
    }

    public String getPassword() {
        return password;
    }

    public void pressLockCarButton(){
        System.out.printf("pressed LockCarButton");
    }

    public void pressUnlockCarButton(){
        System.out.printf("pressed UnlockCarButton");
    }
}
