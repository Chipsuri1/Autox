package factory;

import configuration.Configuration;
import jdk.jfr.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class CameraV2Factory {
    public static Object build() {
        Object CameraV2Port = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToCameraV2JavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, CameraV2Factory.class.getClassLoader());
            Class CameraV2Class = Class.forName("CameraV2", true, urlClassLoader);

            Object CameraV2Instance = CameraV2Class.getMethod("getInstance").invoke(null);

            CameraV2Port = CameraV2Class.getDeclaredField("port").get(CameraV2Instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CameraV2Port;
    }
}
