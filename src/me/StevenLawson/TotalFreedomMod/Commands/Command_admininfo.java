package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Shows you how to become a admin.", usage = "/<command>", aliases = "ai")
public class Command_admininfo extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {

        playerMsg(ChatColor.AQUA + "The following is accurate as of 07/27/14");
        playerMsg(ChatColor.AQUA + "To apply for admin you need to go to the forums at http://packsfreedom.icyboards.net");
        playerMsg(ChatColor.AQUA + "Read the template!");
        playerMsg(ChatColor.AQUA + "Make a new thread called My Super Admin Application");
        playerMsg(ChatColor.RED + "The PacksFreedom server highly recommends that you do not ask for reccomendations for admin. It will decrease your chances of becoming a Super Admin");
        playerMsg(ChatColor.AQUA + "Good Luck!");
        return true;

    }
}
