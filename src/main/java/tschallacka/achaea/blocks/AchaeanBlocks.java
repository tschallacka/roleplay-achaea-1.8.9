package tschallacka.achaea.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tschallacka.achaea.blocks.stone.chimeran.ChimeranBlock;
import tschallacka.achaea.item.blocks.ItemBlockMeta;

public final class AchaeanBlocks {
    public static List<Block> blocks = new ArrayList<Block>(1);
    public static ChimeranBlock CHIMERAN;
    /**
     * Called in preInit
     * @see tschallacka.achaea.proxy.CommonProxy#preInit(net.minecraftforge.fml.common.event.FMLPreInitializationEvent)
     */
    public static void createBlocks() 
    {
        AchaeanBlocks blocks = new AchaeanBlocks();
        blocks.registerMetaBlock(CHIMERAN = new ChimeranBlock(), "chimeran_block");
    }
    
    private AchaeanBlocks() {
        
    }
    
    /**
     * Registers a block to the gameregistry.<BR/>
     * There needs to be a blockstate json in <BR/>
     *  /assets/achaea/blockstates/NAME.json with the same name as give the the variable name
     * @param block The block to register
     * @param name The unlocalised and registry name to give it
     * @return The block itself.
     */
    private Block registerMetaBlock(Block block, String name) 
    {
        block.setUnlocalizedName(name);
        block.setRegistryName(name);
        GameRegistry.registerBlock(block, ItemBlockMeta.class, name);
        return block;
    }
    
}
