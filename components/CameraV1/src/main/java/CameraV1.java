public class CameraV1 {

    private static final CameraV1 instance = new CameraV1();

    public Port port;

    private final String version = "V1";
    private boolean isOn = false;

    public CameraV1(){
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

    public static CameraV1 getInstance() {
        return instance;
    }

    public class Port implements ICameraV1{

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
