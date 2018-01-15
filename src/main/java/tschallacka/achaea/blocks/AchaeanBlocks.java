package tschallacka.achaea.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tschallacka.achaea.blocks.interfaces.IMetaBlock;
import tschallacka.achaea.blocks.interfaces.INamedProperty;
import tschallacka.achaea.blocks.stone.chimeran.AchaeanStair;
import tschallacka.achaea.blocks.stone.chimeran.ChimeranBlock;
import tschallacka.achaea.blocks.stone.chimeran.ChimeranType;
import tschallacka.achaea.item.blocks.ItemBlockMeta;

public final class AchaeanBlocks {
    /**
     * This list will be used to get all the variant blocks that need to be registered<BR/>
     * for rendering. All blocks registered here will be rendered as items in creative tab etc...
     */
    private static List<Block> blocks = new ArrayList<Block>(2);
    
    public static ChimeranBlock CHIMERAN;
    
    public static AchaeanStair CHIMERAN_BRICK_STAIR;

	public static AchaeanStair CHIMERAN_CRACKED_BRICK_STAIR;

	public static AchaeanStair CHIMERAN_CONCRETE_STAIR;
    /**
     * Called in preInit
     * @see tschallacka.achaea.proxy.CommonProxy#preInit(net.minecraftforge.fml.common.event.FMLPreInitializationEvent)
     */
    public static void createBlocks() 
    {
        AchaeanBlocks blocks = new AchaeanBlocks();
        blocks.registerMetaBlock(CHIMERAN = new ChimeranBlock(), "chimeran_block");
        CHIMERAN_BRICK_STAIR = blocks.registerStair(CHIMERAN, ChimeranType.CHIMERAN_BRICK);
        CHIMERAN_CRACKED_BRICK_STAIR = blocks.registerStair(CHIMERAN, ChimeranType.CHIMERAN_BRICK_CRACKED);
        CHIMERAN_CONCRETE_STAIR = blocks.registerStair(CHIMERAN, ChimeranType.CHIMERAN_CONCRETE);
    }
    
    public static AchaeanStair registerStair(Block parent, INamedProperty property) {
        IBlockState state = parent.getStateFromMeta(property.getID());
        AchaeanStair stair = new AchaeanStair(state, property.getName()+"_stair");
        GameRegistry.registerBlock(stair);
        blocks.add(stair);
        return stair;
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
        this.blocks.add(block);
        return block;
    }
    
    /**
     * returns a copy of the current list.
     * @return java.util.List&lt;Block&gt;
     */
    public static List<Block> getBlocks() 
    {
        List<Block> list = new ArrayList(blocks);
        return list;
    }
    
    private AchaeanBlocks() {
        
    }
    
}
