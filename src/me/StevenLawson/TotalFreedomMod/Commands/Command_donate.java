package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Tells a player how to donate", usage = "/<command>")
public class Command_donate extends TFM_Command
{

	@Override
	public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        sender.sendMessage(ChatColor.RED + "To donate go to: http://www.planetminecraft.com/server/packsfreedom-all-op---minecraft-179/");
        sender.sendMessage(ChatColor.RED + "Then give a diamond");
        sender.sendMessage(ChatColor.GREEN + "Notice: By donating you accept that your rank may be taken away at any time. PlanetMinecraft account required.");
		return true;
	}

}
