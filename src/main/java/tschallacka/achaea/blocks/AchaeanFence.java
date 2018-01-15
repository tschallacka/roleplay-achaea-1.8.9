package tschallacka.achaea.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tschallacka.achaea.Achaea;

public class AchaeanFence extends BlockFence {

    public AchaeanFence(Material material) {
        super(material);
        this.setCreativeTab(Achaea.creativeTab);
    }
    public AchaeanFence(Material material, MapColor mapcolor) {
        super(material, mapcolor);
        this.setCreativeTab(Achaea.creativeTab);
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
    
    @Override
    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState state = worldIn.getBlockState(pos);
        
        if(state.getBlock() instanceof AchaeanWall) {
            return false;
        }
        
        return super.canConnectTo(worldIn, pos);
    }
    
    
}