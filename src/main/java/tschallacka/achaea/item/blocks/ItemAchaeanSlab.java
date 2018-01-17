package tschallacka.achaea.item.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import tschallacka.achaea.blocks.AchaeanSlab;
import tschallacka.achaea.blocks.stone.slab.StoneDoubleSlab;
import tschallacka.achaea.blocks.stone.slab.StoneHalfSlab;
import tschallacka.achaea.blocks.stone.slab.StoneSlab;

/**
* Wrapper around ItemSlab to allow init from GameRegistry.
*
*/
public class ItemAchaeanSlab extends ItemSlab {
    private boolean stacked = false;
    /**
     * Initializes a new instance of the ItemBlockStainedBrickSlab class.
     * @param block the block behind the item.
     * @param slab the half height slab.
     * @param doubleSlab the full height slab.
     * @param stacked whether or not the block is the stacked version.
     */
    public ItemAchaeanSlab(
        final Block block,
        final AchaeanSlab slab,
        final AchaeanSlab doubleSlab,
        final Boolean stacked) {
        super(block, slab, doubleSlab);
        this.load(stacked);
    }
    private void load(boolean stacked) {
        this.stacked = stacked;
    }
    /**
     * Adding the spific variants here so we can reuse the same item class. 
     * Needs specific access because game registry doesn't look at inheritance.
     */
    public ItemAchaeanSlab(
        final Block block,
        final StoneHalfSlab slab,
        final StoneDoubleSlab doubleSlab,
        final Boolean stacked) {
        super(block, slab, doubleSlab);
        this.load(stacked);
    }
    
    
}