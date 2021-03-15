public class CameraV2 {

    private static final CameraV2 instance = new CameraV2();

    public Port port;

    private final String version = "V2";
    private boolean isOn = false;

    public CameraV2(){
        port = new Port();
    }

    //inner methods
    public String innerVersion(){
        return version;
    }

    public boolean innerOn(){
        isOn = true;
        return isOn;
    }

    public boolean innerOff(){
        isOn = false;
        return isOn;
    }

    public static CameraV2 getInstance() {
        return instance;
    }

    public class Port implements ICameraV2{

        public String version(){
            return innerVersion();
        }

        public boolean on(){
            return innerOn();
        }

        public boolean off(){
            return innerOff();
        }
    }
}
