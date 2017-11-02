package core.config;


import core.Main;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URL;
import java.util.List;
import java.util.Properties;


/**
 * Klasa przechowująca konfigurację bazową aplikacji.
 */
public class AppConfig extends Properties {

    private final static String PROP_NAME = "configLocation";

    private final String[] args;

    public AppConfig(String[] args) {
        this.args = args;
        try {
            try (InputStream is = Main.class.getResourceAsStream("/config.properties")) {
                if (is != null)
                    load(is);
            }


            String configFile = System.getProperty(PROP_NAME);
            if (configFile == null)
                configFile = System.getenv(PROP_NAME);


            String prop = getProperty("application.configFile");
            if (prop != null) {
                configFile = prop;
            }


            RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
            List<String> arguments = runtimeMxBean.getInputArguments();

            URL url = null;

            if (configFile != null && configFile.contains("://"))
                url = new URL(configFile);

            if (configFile != null && url == null) {
                File file = new File(configFile);
                if (file.exists())
                    url = file.toURI().toURL();
            }



            if (url != null)
                load(url);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //  this.list(System.out);

    }

    public void load(URL url) throws IOException {
        try (InputStream is = url.openStream()) {
            load(is);
        }
    }

    public String[] getArgumnts() {
        return args;
    }


}
