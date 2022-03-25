package pl.petardekyt.antyafk;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pl.petardekyt.antyafk.general.Storage;
import pl.petardekyt.antyafk.menu.AfkEvent;

public class Manager extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        new Storage().task(this);
        getServer().getPluginManager().registerEvents(new AfkEvent(), this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void move(PlayerMoveEvent event) {
        new Storage().move(event.getPlayer());
    }

}
