package tschallacka.achaea.blocks.blocktype;

import net.minecraft.util.IStringSerializable;
import tschallacka.achaea.blocks.interfaces.INamedProperty;


public enum EnumRotation implements IStringSerializable, INamedProperty {
    NONE(0, "none"),
    SHOW(1,"show"),
    ROTATED(2,"rotated")
    ;

    private int ID;
    private String name;

    private EnumRotation(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getLocalName() {
        return this.name;
    }
    
    public String getName() {
        return this.name;
    }

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