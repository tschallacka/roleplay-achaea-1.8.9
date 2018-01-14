package tschallacka.achaea.client.render.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import tschallacka.achaea.Achaea;
import tschallacka.achaea.blocks.AchaeanBlocks;
import tschallacka.achaea.blocks.interfaces.IMetaBlockName;
import tschallacka.achaea.blocks.interfaces.INamedPropertyEnum;
import tschallacka.achaea.blocks.stone.chimeran.ChimeranType;

public final class BlockRenderRegister 
{
    

	public static void registerItemBlockVariants() 
	{
	    registerAllVariants(AchaeanBlocks.CHIMERAN,ChimeranType.CHIMERAN_BRICK);
	    
	    
	}
	private static void registerAllVariants(Block block, INamedPropertyEnum type) 
    {
	    INamedPropertyEnum[] types = type.getValues();
	    Item item = Item.getItemFromBlock(block);
	    ArrayList<ItemStack> list = new ArrayList<ItemStack>(16);
	    block.getSubBlocks(item, null, list);
	    String blockName = item.getRegistryName().substring(Achaea.MODID.length() + 1);
	    Achaea.log("Registering block items for " + item.getRegistryName());
	    for(ItemStack stack : list) {
	        if(stack != null) {
	            
	            if(block instanceof IMetaBlockName) {
	                IMetaBlockName metaBlock = (IMetaBlockName)block;
	                INamedPropertyEnum property = metaBlock.getTypeByMeta(stack.getMetadata());
	                Achaea.log("    Variant "+stack.getMetadata()+" name " + Achaea.MODID + ":" + property.getName());
	                ModelLoader.setCustomModelResourceLocation(item, 
                            stack.getMetadata(), 
                            new ModelResourceLocation(Achaea.MODID+":block/"+blockName+"/"+property.getName(), "inventory"));
	            }
	            else {
	                ModelLoader.setCustomModelResourceLocation(item, 
                            stack.getMetadata(), 
                            new ModelResourceLocation(type.getName(), "inventory"));
	            }
	        }
	    }
	    
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
	
	private static ResourceLocation getModelResourceLocationFromProperty(INamedPropertyEnum type) 
    {
        return new ResourceLocation(Achaea.MODID
                                    + ":"
                                   // + "block/"
                                    //+ BlockRenderRegister.getClassName(type)
                                    //+ "/"
                                    + type.getName());
    }
	
	private static ResourceLocation getResourceLocationFromProperty(INamedPropertyEnum type) 
	{
	    return new ResourceLocation(Achaea.MODID
	                                + ":"
	                                //+ "block/"
	                                //+ BlockRenderRegister.getClassName(type)
	                                //+ "/"
	                                + type.getName());
	}
	
	
	private static String getClassName(INamedPropertyEnum clazz) {
	    return clazz.getClass()
	           .getSimpleName()
	           .toLowerCase()
	           .replaceAll("/[^A-Za-z]/", "")
	           .trim();
	}
}
