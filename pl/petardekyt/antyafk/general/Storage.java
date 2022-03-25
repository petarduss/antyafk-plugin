package pl.petardekyt.antyafk.general;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.petardekyt.antyafk.menu.AfkMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {

    private static HashMap<Player, Integer> afk_time = new HashMap<>();
    private static HashMap<Player, Integer> kick_time_left = new HashMap<>(); // def 10
    private static ArrayList<Player> afk_players = new ArrayList<>();

    public void checkAll() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!afk_players.contains(p)) {
                if (afk_time.get(p) > 50) {
                    kick_time_left.put(p, 0);
                    afk_players.add(p);
                    openPlayerAfkMenu(p);
                }
            } else {
                kick_time_left.put(p, kick_time_left.getOrDefault(p, 0) + 1);
                if (kick_time_left.get(p) > 10) {
                    move(p);
                    p.kickPlayer(ChatColor.RED + "Ah.., zamiast grac afczyles ;p");
                }
            }
        }
        for (Player p : getAfkPlayers()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 10, 1));
            p.setHealth(20);
            if (!(p.getOpenInventory().getType() == InventoryType.PLAYER)) {
                openPlayerAfkMenu(p);
            }
            if (p.getOpenInventory() == null) {
                openPlayerAfkMenu(p);
            }
        }
    }

    public void openPlayerAfkMenu(Player player) {
        new AfkMenu().open(player);
    }

    public void task(Plugin plugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    afk_time.put(p, afk_time.getOrDefault(p, 0) + 1);
                    checkAll();
                }
            }
        }, 20, 20);
    }

    public void move(Player player) {
        player.removePotionEffect(PotionEffectType.POISON);
        afk_players.remove(player);
        kick_time_left.remove(player);
        afk_time.put(player, 0);
    }

    public List<Player> getAfkPlayers() {
        return afk_players;
    }

    public int getAfkPlayerTime(Player player) {
        return afk_time.getOrDefault(player, 0);
    }

    public int getKickLeftTime(Player player) {
        return kick_time_left.getOrDefault(player, 0);
    }

}
