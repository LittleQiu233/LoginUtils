package net.rmc.lq;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
    @Override
    public void onEnable(){
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)this);
        reloadConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().hidePlayer(e.getPlayer());
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(e.getPlayer());
            if (player != e.getPlayer()) {
                e.getPlayer().hidePlayer(player);
            }
        }
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (!(e.getPlayer().hasPermission("login.bypass"))) {
            e.setCancelled(true);

            e.getPlayer().sendMessage(getConfig().getString("Message").replace("&","§"));
        }
    }
}
