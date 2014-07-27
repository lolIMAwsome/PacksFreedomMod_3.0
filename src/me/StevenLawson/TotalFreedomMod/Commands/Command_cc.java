package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level=AdminLevel.SUPER, source=SourceType.BOTH)
@CommandParameters(description="Clears the Chat", usage="/<command>", aliases="clearchat")
public class Command_cc
  extends TFM_Command
{
  @Override
  public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
  {
    for (int i = 0; i <= 100; i++) {
      TFM_Util.bcastMsg("");
    }
    TFM_Util.adminAction(sender.getName(), "Cleared that chat screen", false);
    return true;
  }
}
