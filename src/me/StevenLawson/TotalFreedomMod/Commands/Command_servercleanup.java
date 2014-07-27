package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Runs the cleanup system.", usage = "/<command>")
public class Command_servercleanup extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        TFM_Util.bcastMsg(ChatColor.RED + "Starting server cleanup.");

        if (senderIsConsole)
        {
            server.dispatchCommand(sender, "creative -a");
            server.dispatchCommand(sender, "opall");
            server.dispatchCommand(sender, "invis smite");
            server.dispatchCommand(sender, "rd");
            server.dispatchCommand(sender, "saconfig clean");
            server.dispatchCommand(sender, "tfipbanlist purge");
            server.dispatchCommand(sender, "tfbanlist purge");
            TFM_Util.bcastMsg(ChatColor.GREEN + "Server reset done! Reloading server...");
            server.dispatchCommand(sender, "reload");
        }

        return true;
    }
}
