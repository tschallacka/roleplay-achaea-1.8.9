package tschallacka.achaea.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tschallacka.achaea.Achaea;
import tschallacka.achaea.blocks.blocktype.EnumRotation;

public abstract class AchaeanWall extends AchaeanFence {
    /**
     * Fuck the bloody alphabetic sorting!
     */
    public static final PropertyEnum aFREE = PropertyEnum.create("afree",EnumRotation.class);
    public AchaeanWall(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(Achaea.creativeTab);
    }
    
    public AchaeanWall(Material material, MapColor mapcolor)  {
        super(material,mapcolor);
        this.setCreativeTab(Achaea.creativeTab);
    }
    
    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos).getBlock();
        if(block == Blocks.barrier || block == Blocks.air) {
            return false;
        }
        if(block instanceof AchaeanWall) {
            return (block.getMaterial() == this.getMaterial()); 
        }
        if((block != this && !(block instanceof BlockFenceGate))) {
            if((block.getMaterial().isOpaque() && block.isFullCube())) {
                if(block.getMaterial() == Material.gourd) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        boolean flag = this.canConnectTo(worldIn, pos.north());
        boolean flag1 = this.canConnectTo(worldIn, pos.south());
        boolean flag2 = this.canConnectTo(worldIn, pos.west());
        boolean flag3 = this.canConnectTo(worldIn, pos.east());
        float f = 0.25F;
        float f1 = 0.75F;
        float f2 = 0.25F;
        float f3 = 0.75F;
        float f4 = 1.0F;

        if (flag)
        {
            f2 = 0.0F;
        }

        if (flag1)
        {
            f3 = 1.0F;
        }

        if (flag2)
        {
            f = 0.0F;
        }

        if (flag3)
        {
            f1 = 1.0F;
        }

        if (flag && flag1 && !flag2 && !flag3)
        {
            f4 = 0.8125F;
            f = 0.3125F;
            f1 = 0.6875F;
        }
        else if (!flag && !flag1 && flag2 && flag3)
        {
            f4 = 0.8125F;
            f2 = 0.3125F;
            f3 = 0.6875F;
        }

        this.setBlockBounds(f, 0.0F, f2, f1, f4, f3);
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        this.maxY = 1.5D;
        return super.getCollisionBoundingBox(worldIn, pos, state);
    }
    
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) 
    {
        boolean north = this.canConnectTo(worldIn, pos.north());
        boolean south = this.canConnectTo(worldIn, pos.south());
        boolean east = this.canConnectTo(worldIn, pos.east());
        boolean west = this.canConnectTo(worldIn, pos.west());
        boolean up = this.canConnectTo(worldIn, pos.up());
        EnumRotation rotation = EnumRotation.NONE;
        if(!up && north && south && !west && !east) {
            rotation = EnumRotation.SHOW;
        }
        if(!up && !north && !south && west && east) {
            rotation = EnumRotation.ROTATED;
            
        }
        return state.withProperty(aFREE, rotation)
                .withProperty(NORTH, Boolean.valueOf(north))
                .withProperty(EAST,  Boolean.valueOf(east))
                .withProperty(SOUTH, Boolean.valueOf(south))
                .withProperty(WEST,  Boolean.valueOf(west));
        
    }

}
