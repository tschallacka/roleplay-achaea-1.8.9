package tschallacka.achaea.item.blocks;


import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import tschallacka.achaea.blocks.interfaces.IMetaBlock;

public class ItemBlockMeta extends ItemBlock {

    public ItemBlockMeta(Block block) {
        super(block);
        if (!(block instanceof IMetaBlock)) {
            throw new IllegalArgumentException(String.format("The given Block %s is not an instance of ISpecialBlockName!", block.getUnlocalizedName()));
        }
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "." + ((IMetaBlock)this.block).getSpecialName(stack);
    }
}