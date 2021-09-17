package dansplugins.currencies.eventhandlers;

import dansplugins.currencies.Currencies;
import dansplugins.currencies.managers.ConfigManager;
import dansplugins.currencies.managers.CurrencyManager;
import dansplugins.currencies.objects.Currency;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class CraftingHandler implements Listener {

    @EventHandler()
    public void handle(CraftItemEvent event) {

        if (!ConfigManager.getInstance().getBoolean("disallowCrafting")) {
            if (Currencies.getInstance().isDebugEnabled()) { System.out.println("[DEBUG] Crafting with currencies is allowed and therefore will not be ."); }
            return;
        }

        CraftingInventory inventory = event.getInventory();

        for (ItemStack itemStack : inventory.getMatrix()) {
            if (itemStack == null) {
                continue;
            }
            if (Currencies.getInstance().isDebugEnabled()) {
                System.out.println("Looking at item: " + itemStack.toString());
            }
            if (CurrencyManager.getInstance().isCurrency(itemStack)) {
                event.getWhoClicked().sendMessage(ChatColor.RED + "You can't use currencies in crafting recipes.");
                event.setCancelled(true);
                return;
            }
        }
        if (Currencies.getInstance().isDebugEnabled()) { System.out.println("[DEBUG] Crafting was not cancelled."); }
    }

}
