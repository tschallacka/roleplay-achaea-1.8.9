package tschallacka.achaea.blocks.stone.slab;

import java.util.List;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tschallacka.achaea.blocks.AchaeanBlocks;
import tschallacka.achaea.blocks.AchaeanSlab;
import tschallacka.achaea.blocks.blocktype.StoneSlabType;
import tschallacka.achaea.blocks.blocktype.StoneWallType;
import tschallacka.achaea.blocks.interfaces.IMetaBlock;

/**
 * Stone slab. Uses halfSlab and doubleSlab child objects when registering.
 * Keep in mind that the slab item needs a specific receptor for the child object classes
 * otherwise the slabs don't get registered property.
 * 
 * @see tschallacka.achaea.item.blocks.ItemAchaeanSlab
 * @author mdibb
 *
 */
public abstract class StoneSlab extends AchaeanSlab implements IMetaBlock
{
    public static final PropertyEnum<StoneSlabType> VARIANT = PropertyEnum.<StoneSlabType>create("variant", StoneSlabType.class);
    public static final PropertyBool SEAMLESS = PropertyBool.create("seamless");
     
    public StoneSlab()
    {
        super(Material.rock);
        IBlockState blockState = this.blockState.getBaseState();
        blockState = blockState.withProperty(VARIANT, StoneSlabType.CHIMERAN_BRICK_SLAB);
        if (!this.isDouble()) {
            blockState = blockState.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }

        setDefaultState(blockState);
    }

    @Override
    public final String getUnlocalizedName(int metadata) {
        return this.getUnlocalizedName() + "." + StoneSlabType.byMetadata(metadata).getUnlocalizedName();
    }
    
    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    public Object getVariant(ItemStack stack)
    {
        return StoneSlabType.byMetadata(stack.getMetadata() & 7);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        for (StoneSlabType type : StoneSlabType.values())
        {
            list.add(new ItemStack(itemIn, 1, type.getMetadata()));   
        }
        
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, StoneSlabType.byMetadata(meta & 7));

        if (this.isDouble())
        {
            iblockstate = iblockstate.withProperty(SEAMLESS, Boolean.valueOf((meta & 8) != 0));
        }
        else
        {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((StoneSlabType)state.getValue(VARIANT)).getMetadata();

        if (this.isDouble())
        {
            if (((Boolean)state.getValue(SEAMLESS)).booleanValue())
            {
                i |= 8;
            }
        }
        else if (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    protected BlockState createBlockState()
    {
        return this.isDouble() ? new BlockState(this, new IProperty[] {SEAMLESS, VARIANT}): new BlockState(this, new IProperty[] {HALF, VARIANT});
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return ((StoneSlabType)state.getValue(VARIANT)).getMetadata();
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state)
    {
        return ((StoneSlabType)state.getValue(VARIANT)).getMapColor();
    }
    
    public StoneSlabType getTypeByMeta(int meta) { 
        StoneSlabType type;
        
        switch( meta ) {
            case 0: type = StoneSlabType.CHIMERAN_BRICK_SLAB;break;
            case 1: type = StoneSlabType.CHIMERAN_CONCRETE_SLAB;break;
            case 2: type = StoneSlabType.CHIMERAN_BRICK_CRACKED_SLAB;break;
            default: type =  StoneSlabType.CHIMERAN_BRICK_SLAB;break;
        }
        
        return type;
    }
    
    @Override
    public String getSpecialName(ItemStack stack) 
    {
        return this.getTypeByMeta(stack.getItemDamage()).getName();
    }
}
