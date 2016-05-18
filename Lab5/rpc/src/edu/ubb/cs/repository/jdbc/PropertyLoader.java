package edu.ubb.cs.repository.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyLoader {
	protected static Properties database;
	protected static InputStream is;
	public static String getProperty(String key){
       if (is==null)
       {
           is = PropertyLoader.class.getResourceAsStream("/database.properties");
       }
       if (database== null)
       {
           database = new Properties();
           try {
               database.load(is);
           } catch (IOException ex) {
               Logger.getLogger(PropertyLoader.class.getName()).log(Level.SEVERE, null, ex);
           }
       };
       return database.getProperty(key);
   } 
   
}
