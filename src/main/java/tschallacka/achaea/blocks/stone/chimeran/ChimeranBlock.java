package tschallacka.achaea.blocks.stone.chimeran;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tschallacka.achaea.Achaea;
import tschallacka.achaea.blocks.interfaces.IMetaBlockName;
/**
 * https://bedrockminer.jimdo.com/modding-tutorials/basic-modding-1-8/first-block/
 */
public class ChimeranBlock extends Block implements IMetaBlockName
{
    public static final PropertyEnum TYPE = PropertyEnum.create("type", ChimeranType.class);

	public ChimeranBlock(String unlocalizedName, Material material, float hardness, float resistance) 
	{
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Achaea.creativeTab);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel("pickaxe", 2);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, ChimeranType.CHIMERAN_BRICK));
    }
	
	public ChimeranBlock(String unlocalizedName, float hardness, float resistance) 
	{
        this(unlocalizedName, Material.rock, hardness, resistance);
    }

    public ChimeranBlock(String unlocalizedName) 
    {
        this(unlocalizedName, 2.0f, 10.0f);
    }

    @Override
    protected BlockState createBlockState() 
    {
        return new BlockState(this, new IProperty[] { TYPE });
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        
        
        return getDefaultState().withProperty(TYPE, this.getTypeByMeta(meta));
        
    }
    public ChimeranType getTypeByMeta(int meta) 
    {
        ChimeranType type;
        
        switch( meta ) {
            case 1: type = ChimeranType.CHIMERAN_BRICK_DRAIN;break;
            case 2: type = ChimeranType.CHIMERAN_BRICK_GRAFITTI;break;
            case 3: type = ChimeranType.CHIMERAN_CONCRETE;break;
            case 4: type = ChimeranType.CHIMERAN_BRICK_CRACKED;break;
            default:type = ChimeranType.CHIMERAN_BRICK;break;
        }
        return type;
    }
    
    @Override
    public String getSpecialName(ItemStack stack) 
    {
        return this.getTypeByMeta(stack.getItemDamage()).getName();
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) 
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
    }
    
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) 
    {
        list.add(new ItemStack(itemIn, 1, 0)); //Meta 0
        list.add(new ItemStack(itemIn, 1, 1)); //Meta 1
        list.add(new ItemStack(itemIn, 1, 2)); //Meta 2
        list.add(new ItemStack(itemIn, 1, 3)); //Meta 3
        list.add(new ItemStack(itemIn, 1, 4)); //Meta 4 
    }
    
    @Override
    public int damageDropped(IBlockState state) 
    {
        return getMetaFromState(state);
    }
    
    @Override
    public int getMetaFromState(IBlockState state) 
    {
        ChimeranType type = (ChimeranType) state.getValue(TYPE);
        return type.getID();
    }
}
