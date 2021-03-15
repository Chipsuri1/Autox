package configuration;

import factory.CameraV1Factory;
import vehicle.body.Lidar;
import vehicle.body.LidarNG;
import vehicle.engine.Engine;
import vehicle.engine.EngineNG;

public enum Configuration {
    instance;

    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String commonPathToJavaArchive = userDirectory + fileSeparator + "components" + fileSeparator;

    public String lineSeparator = System.getProperty("line.separator");

    //cameraV1
    public String pathToCameraV1JavaArchive = commonPathToJavaArchive + "CameraV1" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "cameraV1.jar";

    //cameraV2
    public String pathToCameraV2JavaArchive = commonPathToJavaArchive + "CameraV2" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "cameraV2.jar";

    //autoX
    public String parameter = "-config";
    public String secretKey = "dhbw";

    public CameraV1Factory cameraV1Factory;
    public Engine engine = new EngineNG();
    public Lidar lidar = new LidarNG();
}
