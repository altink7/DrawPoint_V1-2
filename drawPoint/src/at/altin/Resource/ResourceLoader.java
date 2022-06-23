package at.altin.Resource;

import java.io.InputStream;

public class ResourceLoader {
    public ResourceLoader() {
    }

    public static InputStream load(String path) {
        InputStream input = ResourceLoader.class.getResourceAsStream(path);
        if (input == null) {
            input = ResourceLoader.class.getResourceAsStream("/" + path);
        }

        return input;
    }
}