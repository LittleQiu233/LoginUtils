package net.rmc.lq.Listener;

import net.rmc.lq.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        if (Main.disablesendmessage && !(e.getPlayer().hasPermission("loginutils.bypass"))) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Main.instance.getConfig().getString("backmessage").replace("&","§"));
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (Main.NoJoinMessage){
          e.setJoinMessage(null);
        }
        if (Main.Hideplayer) {
            e.getPlayer().hidePlayer(e.getPlayer());
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.hidePlayer(e.getPlayer());
                if (player != e.getPlayer()) {
                    e.getPlayer().hidePlayer(player);
                }
            }
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (Main.NoLeaveMessage) {
            e.setQuitMessage(null);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDmg(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (Main.noDamage && Main.instance.getConfig().getStringList("noDamageWorlds").contains(((Player) e.getEntity()).getPlayer().getWorld().getName())){
                    e.setCancelled(true);
                }else{
                    e.setCancelled(false);
                }
            }
        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(Main.noDrop && Main.instance.getConfig().getStringList("noDropWorlds").contains(e.getPlayer().getWorld().getName())){
            e.setCancelled(true);
        }else{
            e.setCancelled(false);
        }
    }
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        Player p = (Player)e.getEntity();
        if(Main.noHungry && Main.instance.getConfig().getStringList("noHungryWorlds").contains(p.getPlayer().getWorld().getName())){
            p.setFoodLevel(20);
            e.setCancelled(true);
        }else{
            e.setCancelled(false);
        }
    }
}