package tschallacka.achaea.settings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tschallacka.achaea.Achaea;

public class Config {
	
	private static Config instance;
	
 	private Configuration config;
 	
 	private final String CATEGORY_GENERAL = "General";
    private final String CATEGORY_SERVER = "Server";
    
    public static void init(FMLPreInitializationEvent event) 
    {
    	try {
    		instance = new Config();
            instance.initialize(event.getSuggestedConfigurationFile());
        }
        catch (Exception e) {
            Achaea.error("Achaea failed to load preferences. Reverting to defaults", e);
        }
        finally {
            if (instance != null) {
                instance.save();
            } 
        }
    	Achaea.log("Configuration loaded."+(instance.isAwesomeMod() ? "...*blush*.... No, you are awesome!":""));
    }
    
    public List<String> getConfigElements() 
    {
    	List<String> configElementsList = null;
    	if(configElementsList == null) {
    		configElementsList = new ArrayList<String>(2);
    		configElementsList.add(CATEGORY_GENERAL);
    		configElementsList.add(CATEGORY_SERVER);
    	}
    	return configElementsList;
    }
    
    private void initialize(File file) {
        
    	config = new Configuration(file);
        
        config.addCustomCategoryComment(CATEGORY_GENERAL, "General Settings");
        config.addCustomCategoryComment(CATEGORY_SERVER,  "Server settings");
        
        config.load();
        
        syncConfigurable();
    }
    
    private void save() 
    {
    	if(config != null) {
    		config.save();
    	}
    }
    
    protected String getString(String category, 
    								   String name_in_file, 
    								   String comment, 
    								   String defaultValue) {
    	Property prop = config.get(category, name_in_file, defaultValue);
        prop.comment = comment;
        return prop.getString();
    }
    
    protected int getInteger(String category,
    								   String name_in_file, 
    								   String comment, 
    								   int defaultValue) {
    	Property prop = config.get(category, name_in_file, defaultValue);
        prop.comment = comment;
        return prop.getInt();
    }
    
    protected double getDouble(String category,
										String name_in_file, 
									    String comment, 
									    double defaultValue) {
		Property prop = config.get(category, name_in_file, defaultValue);
		prop.comment = comment;
		return prop.getDouble();
	}
    
    protected boolean getBoolean(String category,
									String name_in_file, 
								    String comment, 
								    boolean defaultValue) {
		Property prop = config.get(category, name_in_file, defaultValue);
		prop.comment = comment;
		return prop.getBoolean();
	}
    
    public static boolean isAwesomeMod() 
    {
		return instance.isAwesomeMod;
	}

	private boolean isAwesomeMod = false;
    
    public void syncConfigurable() 
    {	
	    this.isAwesomeMod = this.getBoolean(
	    						CATEGORY_GENERAL, 
	    						"awesome_mod", 
	    						"If you consider this an awesome mod feel free to set to true.", 
	    						this.isAwesomeMod
		);
	    
    	
        
    }
}


