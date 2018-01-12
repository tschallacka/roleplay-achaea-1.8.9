package tschallacka.achaea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.relauncher.Side;
import tschallacka.achaea.gui.creative.tab.AchaeanCreativeTab;
import tschallacka.achaea.proxy.CommonProxy;

@Mod(modid = Achaea.MODID, 
	 version = Achaea.VERSION, 
	 name = Achaea.MODNAME)
public class Achaea 
{
	public static final String MODID = "achaea";
    public static final String VERSION = "1.8.9-1.0.0.a1";
    public static final String MODNAME = "Achaean Roleplay";
    
	@Mod.Instance(Achaea.MODID)
    public static Achaea instance;
	
	/**
	 * The proxy instance.
	 * @see tschallacka.achaea.proxy.CommonProxy
	 * @see tschallacka.achaea.proxy.client.ClientProxy
	 * @see tschallacka.achaea.proxy.server.ServerProxy
	 */
	@SidedProxy(clientSide = "tschallacka.achaea.proxy.client.ClientProxy", 
                serverSide = "tschallacka.achaea.proxy.common.ServerProxy")
	public static CommonProxy proxy;
    
	public static AchaeanCreativeTab creativeTab;
	
	public Achaea() {
		super();
	}
	
	
    
	
	private static boolean IS_SHUTTING_DOWN;
	
	
	public static final Logger log = LogManager.getLogger("ACHAEA");;
	
	public static void log(Object log) 
	{
		Achaea.log.info(log);
	}
	
	public static void error(Object log) 
	{
		Achaea.log.error(log);
	}
	
	public static void error(Object log, Throwable t) 
	{
		Achaea.log.error(log, t);
	}
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) 
    {
    	Achaea.log.warn("Preinit starting");
    	MinecraftForge.EVENT_BUS.register((Object)Achaea.instance);
        
    	if(Loader.isModLoaded("Thaumcraft")) {
    	
    		Achaea.log("Hello Thaumaturge! Let the knowledge infuse you!");
    	}
    	else {
    		Achaea.log("Shame, no Thaumcraft.");
    	}
    	
    	e.getModMetadata().version = Achaea.VERSION;
    	
    	Achaea.proxy.preInit(e);
    	
    	
    	Achaea.log("Preinit ended");
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) 
    {
    	Achaea.log("init starting");
    	
    	Achaea.proxy.init(event);
    	
    	Achaea.log.warn("init ended");
    }
    
    @EventHandler 
    public void postInit(FMLPostInitializationEvent event) 
    { 
    	Achaea.proxy.postInit(event); 
    } 

	
	@EventHandler
   	public void start(FMLServerStartingEvent event) 
	{
		if(event.getSide() == Side.SERVER) {
	   		
	    	Achaea.IS_SHUTTING_DOWN = false;
	    	
		}
   	}
    
		
    @EventHandler
    public void stop(FMLServerStoppingEvent event) 
    {
   		if(event.getSide() == Side.SERVER) {
   			
	   		Achaea.IS_SHUTTING_DOWN = true;
	   		
   		}
    }
    
    static {
        Achaea.creativeTab = new AchaeanCreativeTab(CreativeTabs.getNextID(), Achaea.MODID);
    }
}
