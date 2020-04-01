package net.rmc.lq;

import net.rmc.lq.Commands.reload;
import net.rmc.lq.Listener.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    public static boolean NoJoinMessage;
    public static boolean NoLeaveMessage;
    public static boolean disablesendmessage;
    public static boolean Hideplayer;
    public static boolean noDrop;
    public static boolean noWeahter;
    public static boolean noDamage;
    public static boolean noHungry;
    public static boolean noBreak;
    public static boolean noPlace;
    FileConfiguration config = getConfig();

    public static Main instance = null;{
        instance = this;
    }
    @Override
    public void onEnable(){
        config();
        registerEvent();
        registerCommands();
    }
    public void config(){
        instance = this;
        saveDefaultConfig();
        NoJoinMessage = config.getBoolean("NoJoinMessage");
        NoLeaveMessage = config.getBoolean("NoLeaveMessage");
        disablesendmessage = config.getBoolean("disablesendmessage");
        Hideplayer = config.getBoolean("Hideplayer");
        noDrop = config.getBoolean("NoDrop");
        noHungry = config.getBoolean("NoHungry");
        noDamage = config.getBoolean("NoDamage");
        noWeahter = config.getBoolean("NoWeather");
        noBreak = config.getBoolean("noBreak");
        noPlace = config.getBoolean("noPlace");
    }
    public void registerEvent(){
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new WorldListener(),this);
    }
    public void registerCommands(){
        Bukkit.getPluginCommand("loginutils").setExecutor(new reload());
    }
}