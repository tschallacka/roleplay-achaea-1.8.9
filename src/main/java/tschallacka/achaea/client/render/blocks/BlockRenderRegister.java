package tschallacka.achaea.client.render.blocks;

import java.util.ArrayList;
import java.util.List;

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
import tschallacka.achaea.blocks.interfaces.IMetaBlock;
import tschallacka.achaea.blocks.interfaces.INamedPropertyEnum;
import tschallacka.achaea.blocks.stone.chimeran.ChimeranType;

public final class BlockRenderRegister 
{
    private int cutLength = 0;
    private BlockRenderRegister() 
    {
        this.cutLength = Achaea.MODID.length() + 1;
    }
    
	public static void registerItemBlockVariants() 
	{
	    BlockRenderRegister registrar = new BlockRenderRegister();
	    registrar.registerAllVariants(AchaeanBlocks.CHIMERAN);
	}
	
	/**
	 * This method loops through all variants that are offered to creativeTab.
	 * When an item should not be registered to a tab but only available ingame,
	 * make a check that the tab offered is NULL
	 * 
	 * It then checks if the block is an instance of IMetaBlockName.
	 * If it is it will use the names provided in getSpecialName() to register the item variant models in
	 * assets/achaea/models/item/block/REGISTRY_NAME/SPECIAL_NAME.json
	 * 
	 * If it's not it will register the block under the block name.
	 * assets/achaea/models/item/block/REGISTRY_NAME/REGISTRY_NAME.json
	 * 
	 * @see net.minecraft.block.Block#getSubBlocks(Item, net.minecraft.creativetab.CreativeTabs, List)
	 * @see  tschallacka.achaea.blocks.interfaces.IMetaBlock
	 * @param block The Block to register
	 * @param type
	 */
	private void registerAllVariants(Block block) 
    {
	    
	    Item item = Item.getItemFromBlock(block);
	    
	    String blockName = this.getName(item);
	    
	    Achaea.log("Registering block items for " + item.getRegistryName());
	    
	    for(ItemStack stack : getItems(block)) {
	        if(stack != null) {	            
	            if(block instanceof IMetaBlock) {
	                
	              this.registerMetaBlock((IMetaBlock)block, stack, blockName);
	            }
	            else {
	                this.registerBlock(item, stack, Achaea.MODID + ":block/" + blockName + "/" + blockName);
	            }
	        }
	    }
	    
	}
	
	private void registerMetaBlock(IMetaBlock block, ItemStack stack, String blockName) {
	    
        String variantName = block.getSpecialName(stack);
        
        Achaea.log("    Variant "+stack.getMetadata()+" name " + Achaea.MODID + ":" + variantName);
        
        this.registerBlock(stack.getItem(), 
                            stack, 
                            Achaea.MODID + ":block/" + blockName + "/" + variantName);
	}
	
	private void registerBlock(Item item, ItemStack stack, String name) {
	    ModelLoader.setCustomModelResourceLocation(item, 
                stack.getMetadata(), 
                new ModelResourceLocation(name, "inventory"));
	}
	
	private String getName(Item item) 
	{
	    return item.getRegistryName().substring(this.cutLength);
	}
	
	private List<ItemStack> getItems(Block block) {
	    Item item = Item.getItemFromBlock(block);
	    ArrayList<ItemStack> list = new ArrayList<ItemStack>(16);
        block.getSubBlocks(item, null, list);
        return list;
	}
}
