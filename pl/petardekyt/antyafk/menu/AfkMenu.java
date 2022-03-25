package pl.petardekyt.antyafk.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Random;

public class AfkMenu {

    public void open(Player player) {

        Inventory inv = Bukkit.createInventory(player, 27, ChatColor.YELLOW + "AntyAFK");
        int random = new Random().nextInt(26) - 1;

        ItemStack click = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = click.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Kliknij");
        click.setItemMeta(meta);

        for (int x = 26;x>=0;x--) {
            inv.setItem(x, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15));
        }

        inv.setItem(random, click);
        player.openInventory(inv);

    }

}
