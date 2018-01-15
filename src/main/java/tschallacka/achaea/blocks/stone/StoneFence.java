package tschallacka.achaea.blocks.stone;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tschallacka.achaea.blocks.AchaeanBlocks;
import tschallacka.achaea.blocks.AchaeanFence;
import tschallacka.achaea.blocks.blocktype.StoneFenceType;
import tschallacka.achaea.blocks.interfaces.IMetaBlock;


public class StoneFence extends AchaeanFence implements IMetaBlock 
{
    public static final PropertyEnum TYPE = PropertyEnum.create("type", StoneFenceType.class);
    public StoneFence()
    {
        super(AchaeanBlocks.CHIMERAN.getMaterial());
        this.setHardness(2.0f);
        this.setResistance(10f / 3f);
        this.setHarvestLevel("pickaxe", 2);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, StoneFenceType.CHIMERAN_BRICK_FENCE));
    }
    /**
     * META STUFF START  ===================================================================
     */
    @Override
    public int getMetaFromState(IBlockState state) 
    {
        StoneFenceType type = (StoneFenceType) state.getValue(TYPE);
        return type.getID();
    }
    
    @Override
    protected BlockState createBlockState() 
    {
        return new BlockState(this, new IProperty[] { TYPE, NORTH, EAST, WEST, SOUTH  });
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) 
    {    
        return getDefaultState().withProperty(TYPE, this.getTypeByMeta(meta));
    }
    
    public StoneFenceType getTypeByMeta(int meta) { 
        StoneFenceType type;
        
        switch( meta ) {
            case 0: type = StoneFenceType.CHIMERAN_BRICK_FENCE;break;
            case 1: type = StoneFenceType.CHIMERAN_CONCRETE_FENCE;break;
            case 2: type = StoneFenceType.CHIMERAN_BRICK_CRACKED_FENCE;break;
            default: type =  StoneFenceType.CHIMERAN_BRICK_FENCE;break;
        }
        
        return type;
    }
    
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) 
    {
        list.add(new ItemStack(itemIn, 1, 0)); //brick
        list.add(new ItemStack(itemIn, 1, 1)); //Meta 1
        list.add(new ItemStack(itemIn, 1, 2)); //Meta 2
    }
    
    @Override
    public String getSpecialName(ItemStack stack) 
    {
        return this.getTypeByMeta(stack.getItemDamage()).getName();
    }
    
    /**
     * META STUFF END  ===================================================================
     */

}
