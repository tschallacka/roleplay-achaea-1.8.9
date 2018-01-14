package tschallacka.achaea.proxy.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tschallacka.achaea.client.render.blocks.BlockRenderRegister;
import tschallacka.achaea.proxy.CommonProxy;

public class ClientProxy extends CommonProxy 
{
	public void preInit(FMLPreInitializationEvent e) 
	{
		super.preInit(e);
		BlockRenderRegister.registerItemBlockVariants();
	}
	
	public void init(FMLInitializationEvent e) 
	{
		super.init(e);
		
	}
	
	public void postInit(FMLPostInitializationEvent e) 
	{
		super.postInit(e);
	}
}
