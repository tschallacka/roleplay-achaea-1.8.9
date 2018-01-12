package tschallacka.achaea.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tschallacka.achaea.blocks.AchaeanBlocks;
import tschallacka.achaea.settings.Config;

/**
 * @see tschallacka.achaea.proxy.client.ClientProxy
 * @see tschallacka.achaea.proxy.server.ServerProxy
 */
public class CommonProxy 
{
	/**
	 * Returns the current thread based on side during message handling,
	 * used for ensuring that the message is being handled by the main thread
	 */
	public IThreadListener getThreadFromContext(MessageContext ctx) 
	{
		return ctx.getServerHandler().playerEntity.getServerForPlayer();
	}
	
	public EntityPlayer getPlayerEntity(MessageContext ctx) 
	{
		return ctx.getServerHandler().playerEntity;
	}
	
	/**
	 * @see tschallacka.achaea.proxy.client.ClientProxy#preInit(FMLPreInitializationEvent)
	 * @see tschallacka.achaea.proxy.server.ServerProxy#preInit(FMLPreInitializationEvent)
	 * @param event
	 */
	public void preInit(FMLPreInitializationEvent event) 
	{
		Config.init(event);
		AchaeanBlocks.createBlocks();
		
	}
	
	/**
	 * @see tschallacka.achaea.proxy.client.ClientProxy#init(FMLInitializationEvent)
	 * @see tschallacka.achaea.proxy.server.ServerProxy#init(FMLInitializationEvent)
	 * @param event
	 */
	public void init(FMLInitializationEvent e) 
	{
	
	}
	
	/**
	 * @see tschallacka.achaea.proxy.client.ClientProxy#postInit(FMLPostInitializationEvent)
	 * @see tschallacka.achaea.proxy.server.ServerProxy#postInit(FMLPostInitializationEvent)
	 * @param event
	 */
	public void postInit(FMLPostInitializationEvent e) 
	{
		
	}
}
