package parser.payload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Env {

    public static String getProperties(String name){
        Properties properties  = new Properties();
        String propFileName = "C:\\Users\\KIIT\\Desktop\\Project\\parser\\application.properties";
        try{
            properties.load(Files.newInputStream(Paths.get(propFileName)));
            return properties.getProperty(name);
        }catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }

}
