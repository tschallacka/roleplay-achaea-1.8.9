package tschallacka.achaea.blocks.blocktype;

import net.minecraft.util.IStringSerializable;
import tschallacka.achaea.blocks.interfaces.INamedProperty;

public enum StoneFenceType implements IStringSerializable, INamedProperty
{
    CHIMERAN_BRICK_FENCE(0, "chimeran_brick_fence"), 
    CHIMERAN_CONCRETE_FENCE(1, "chimeran_concrete_fence"), 
    CHIMERAN_BRICK_CRACKED_FENCE(2, "chimeran_brick_cracked_fence"), 
    ; 
    
    private int ID;
    private String name;

    private StoneFenceType(int ID, String name) {
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