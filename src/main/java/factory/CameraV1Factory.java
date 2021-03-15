package factory;

import configuration.Configuration;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class CameraV1Factory {
    public static Object build() {
        Object CameraV1Port = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToCameraV1JavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, CameraV1Factory.class.getClassLoader());
            Class CameraV1Class = Class.forName("CameraV1", true, urlClassLoader);

            Object CameraV1Instance = CameraV1Class.getMethod("getInstance").invoke(null);

            CameraV1Port = CameraV1Class.getDeclaredField("port").get(CameraV1Instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CameraV1Port;
    }
}

