package tschallacka.achaea.blocks.blocktype;

import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;
import tschallacka.achaea.blocks.interfaces.INamedProperty;

public enum StoneSlabType  implements IStringSerializable, INamedProperty
{
    CHIMERAN_BRICK_SLAB(0, MapColor.stoneColor, "chimeran_brick_slab"), 
    CHIMERAN_CONCRETE_SLAB(1,  MapColor.stoneColor, "chimeran_concrete_slab"), 
    CHIMERAN_BRICK_CRACKED_SLAB(2,  MapColor.stoneColor, "chimeran_brick_cracked_slab") 
    ; 
   

    private static final StoneSlabType[] META_LOOKUP = new StoneSlabType[values().length];
    private final int meta;
    private final MapColor mapcolor;
    private final String name;
    private final String unlocalizedName;

    private StoneSlabType(int meta, MapColor mapcolor, String blockname)
    {
        this(meta, mapcolor, blockname, blockname);
    }

    private StoneSlabType(int meta, MapColor mapcolor, String blockname, String unlocalizedname)
    {
        this.meta = meta;
        this.mapcolor = mapcolor;
        this.name = blockname;
        this.unlocalizedName = blockname;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public MapColor getMapColor()
    {
        return this.mapcolor;
    }

    public String toString()
    {
        return this.name;
    }

    public static StoneSlabType byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String getName()
    {
        return this.name;
    }

    public String getUnlocalizedName()
    {
        return this.name;
    }

    static
    {
        for (StoneSlabType type : values())
        {
            META_LOOKUP[type.getMetadata()] = type;
        }
    }

    @Override
    public INamedProperty[] getValues()
    {
        return this.values();
    }

    @Override
    public int getID()
    {
        return this.meta;
    }
}

