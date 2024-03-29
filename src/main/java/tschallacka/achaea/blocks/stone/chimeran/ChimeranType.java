package tschallacka.achaea.blocks.stone.chimeran;

import net.minecraft.util.IStringSerializable;
import tschallacka.achaea.blocks.interfaces.INamedProperty;

public enum ChimeranType implements IStringSerializable, INamedProperty
{
    CHIMERAN_BRICK(0, "chimeran_brick"),
    CHIMERAN_BRICK_DRAIN(1, "chimeran_brick_drain"), 
    CHIMERAN_BRICK_GRAFITTI(2, "chimeran_brick_graffiti"), 
    CHIMERAN_CONCRETE(3, "chimeran_concrete"), 
    CHIMERAN_BRICK_CRACKED(4, "chimeran_brick_cracked"), 
    ; 
    
    private int ID;
    private String name;

    private ChimeranType(int ID, String name) {
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