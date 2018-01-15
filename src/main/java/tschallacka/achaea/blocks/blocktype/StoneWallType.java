package tschallacka.achaea.blocks.blocktype;

import net.minecraft.util.IStringSerializable;
import tschallacka.achaea.blocks.interfaces.INamedProperty;

public enum StoneWallType implements IStringSerializable, INamedProperty
{
    CHIMERAN_BRICK_WALL(0, "chimeran_brick_wall"), 
    CHIMERAN_CONCRETE_WALL(1, "chimeran_concrete_wall"), 
    CHIMERAN_BRICK_CRACKED_WALL(2, "chimeran_brick_cracked_wall"), 
    ; 
    
    private int ID;
    private String name;

    private StoneWallType(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getLocalName() {
        return this.name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public INamedProperty[] getValues() {
        return this.values();
    }


}