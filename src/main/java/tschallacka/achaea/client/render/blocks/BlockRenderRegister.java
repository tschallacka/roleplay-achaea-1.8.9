package tschallacka.achaea.client.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import tschallacka.achaea.Achaea;
import tschallacka.achaea.blocks.AchaeanBlocks;
import tschallacka.achaea.blocks.interfaces.INamedPropertyEnum;
import tschallacka.achaea.blocks.stone.chimeran.ChimeranType;

public final class BlockRenderRegister 
{
    public static void registerBlockRenderer() 
    {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher(); 
        
        register(mesher,  AchaeanBlocks.CHIMERAN, 0);

        
    }
    
    private static void register(ItemModelMesher mesher, Block block, int meta) 
    {   

        String blockName = block.getRegistryName();
        ModelResourceLocation location = new ModelResourceLocation(blockName, "inventory");
        Item item = Item.getItemFromBlock(block);
        mesher.register(item, meta, location);
    }

	public static void registerItemBlockVariants() 
	{
	    
	    ModelBakery.registerItemVariants(Item.getItemFromBlock(AchaeanBlocks.CHIMERAN), new ResourceLocation(AchaeanBlocks.CHIMERAN.getRegistryName())); 
	    
	}
	private static ResourceLocation[] getResourceLocationsForType(INamedPropertyEnum type) 
	{
	    INamedPropertyEnum[] types = type.getValues();
	    ResourceLocation[] locations = new ResourceLocation[types.length];
	    for(int c = 0; c < types.length; c++) {
	        locations[c] = getResourceLocationFromProperty(types[c]);
	    }
	    return locations;
	}
	
	private static ResourceLocation getResourceLocationFromProperty(INamedPropertyEnum type) 
	{
	    return new ResourceLocation(Achaea.MODID+":"+type.getName());
	}
}
