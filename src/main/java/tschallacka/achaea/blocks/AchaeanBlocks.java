package tschallacka.achaea.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tschallacka.achaea.blocks.stone.chimeran.ChimeranBlock;
import tschallacka.achaea.item.blocks.ItemBlockMeta;

public final class AchaeanBlocks {
    
    public static Block CHIMERAN;
    
    public static void createBlocks() 
    {
        GameRegistry.registerBlock(CHIMERAN = new ChimeranBlock("chimeran_block"), ItemBlockMeta.class, "chimeran_block");
    }
    
}
