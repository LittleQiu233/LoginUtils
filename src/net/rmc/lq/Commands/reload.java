package net.rmc.lq.Commands;

import net.rmc.lq.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equals("loginutils")) {
            if (args.length != 1) {
                commandSender.sendMessage("§eLoginUtils §f>> /loginutils reload §7- §c重载插件配置.");
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (commandSender.hasPermission("loginutils.admin")){
                Main.instance.reloadConfig();
                commandSender.sendMessage("§eLoginUtils §f>> §c重载成功.");
            }else{
                commandSender.sendMessage("§eLoginUtils §f>> §c你没有权限执行此命令.");
            }
        }
        return true;
    }
}