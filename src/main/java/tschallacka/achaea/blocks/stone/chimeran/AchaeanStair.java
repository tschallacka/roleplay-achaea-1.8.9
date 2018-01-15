package tschallacka.achaea.blocks.stone.chimeran;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import tschallacka.achaea.Achaea;

/**
 * https://bedrockminer.jimdo.com/modding-tutorials/basic-modding-1-8/first-block/
 */
public class AchaeanStair extends BlockStairs
{

    public AchaeanStair(IBlockState modelState, String name) {
        super(modelState);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(Achaea.creativeTab);
        this.useNeighborBrightness = true;
    }

    
}
