package net.rmc.lq.Listener;

import net.rmc.lq.Main;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWeahterChange(WeatherChangeEvent e){
        if(Main.noWeahter && Main.instance.getConfig().getStringList("noWeatherWorlds").contains(e.getWorld().getName())){
            e.setCancelled(true);
            e.getWorld().setWeatherDuration(0);
            e.getWorld().setThundering(false);
        }else{
            e.setCancelled(false);
        }
    }
    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreakBlock(BlockBreakEvent e){
        if(Main.noBreak && Main.instance.getConfig().getStringList("noBreakWorlds").contains(e.getPlayer().getWorld().getName()) && e.getPlayer().getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
        }
    }
    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onLightning(LightningStrikeEvent e){
        if(Main.noBreak && Main.instance.getConfig().getStringList("noBreakWorlds").contains(e.getWorld().getName())){
            e.setCancelled(true);
        }
    }
    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onIgnite(BlockIgniteEvent e){
        if(e.getCause() != BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL && Main.instance.getConfig().getStringList("noBreakWorlds").contains(e.getPlayer().getWorld().getName()) && Main.noBreak){
            e.setCancelled(true);
        }
    }
    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlaceBlock(BlockPlaceEvent e){
        if(Main.noBreak && Main.instance.getConfig().getStringList("noPlaceWorlds").contains(e.getPlayer().getWorld().getName()) && e.getPlayer().getGameMode() != GameMode.CREATIVE){
            e.setCancelled(true);
        }
    }
}