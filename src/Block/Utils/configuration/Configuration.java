package Block.Utils.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by luis on 23/07/14.
 */
public class Configuration {
    private Properties Configuration;
    public Configuration() throws IOException{
        this.Configuration=new Properties();
        this.Configuration.load(new FileInputStream("Configurations/ConfigurationData.ini"));
    }

    public String getValue(String Key) {
        if(!this.Configuration.containsKey(Key))
            return "clear";
        return this.Configuration.getProperty(Key);
    }
}
