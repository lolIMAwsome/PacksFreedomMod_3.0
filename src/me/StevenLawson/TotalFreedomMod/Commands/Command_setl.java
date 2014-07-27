package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Sets everyone's Worldedit block modification limit to 500.", usage = "/<command>")
public class Command_setl extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        TFM_Util.adminAction(sender.getName(), "Setting everyone's Worldedit block modification limit to 500.", true);
        for (final Player player : server.getOnlinePlayers())
        {
            server.dispatchCommand(Bukkit.getConsoleSender(), "wildcard gcmd ? /limit 500");
        }
        return true;
    }
}
