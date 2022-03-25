package pl.petardekyt.antyafk.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.petardekyt.antyafk.Manager;
import pl.petardekyt.antyafk.general.Storage;

public class AfkEvent implements Listener {

    @EventHandler
    public void menu(InventoryClickEvent e) {

        if (e.getInventory().getTitle() != null) {
            if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.YELLOW + "AntyAfk")) {
                if (e.getCurrentItem() != null) {
                    e.setCancelled(true);
                    if ((e.getCurrentItem() == null) || (e.getCurrentItem().getType().equals(Material.AIR))) {
                        return;
                    }

                    if (!e.getCurrentItem().hasItemMeta()) {
                        return;
                    }

                    new Storage().move((Player) e.getWhoClicked());
                    e.getWhoClicked().closeInventory();
                }
            }
        }

    }
}
