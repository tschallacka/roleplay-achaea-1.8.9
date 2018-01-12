package tschallacka.achaea.gui.creative.tab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AchaeanCreativeTab extends CreativeTabs 
{
    private ArrayList<ItemStack> customitems = new ArrayList<ItemStack>();
    
    /**
     * Adds a custom item to be displayed in the creative menu.
     * @param stack
     */
    public void addCustomItem(ItemStack stack) 
    {
        this.customitems.add(stack.copy());        
    }
    
    public AchaeanCreativeTab(final int tabId, final String ModName) 
    {
        super(tabId, ModName);
    }
    
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() 
    {
        return Items.baked_potato;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllReleventItems(List<ItemStack> list) 
    {
        super.displayAllReleventItems(list);

        Iterator<ItemStack> it = customitems.iterator();
        
        while(it.hasNext()) {
            list.add(it.next().copy());
        }
    }

}
