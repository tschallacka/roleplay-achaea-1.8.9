package tschallacka.achaea.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tschallacka.achaea.Achaea;

public abstract class AchaeanSlab extends BlockSlab
{

    public AchaeanSlab(Material materialIn)
    {
        super(materialIn);  
        if (!this.isDouble()) {
            this.setCreativeTab(Achaea.creativeTab);
        }
        this.useNeighborBrightness = !this.isDouble();
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) 
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
    }
    

}
