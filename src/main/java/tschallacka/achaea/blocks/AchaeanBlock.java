package tschallacka.achaea.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tschallacka.achaea.Achaea;

public class AchaeanBlock extends Block 
{

    public AchaeanBlock(Material materialIn)
    {
        super(materialIn);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) 
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
    }
    
    @Override
    public int damageDropped(IBlockState state) 
    {
        return getMetaFromState(state);
    }
    
    
}