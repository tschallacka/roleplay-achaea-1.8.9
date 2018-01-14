package tschallacka.achaea.blocks.interfaces;

import net.minecraft.item.ItemStack;

public interface IMetaBlockName {
        INamedPropertyEnum getTypeByMeta(int meta);
        
        String getSpecialName(ItemStack stack);
}