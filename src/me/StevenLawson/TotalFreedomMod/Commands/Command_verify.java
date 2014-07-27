package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(
        description = "Tell an imposter to verify.",
        usage = "/<command> [player]")
public class Command_verify extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            return false;
        }
       Player p = getPlayer(args[0]);
       if (p == null){
    	   sender.sendMessage(TotalFreedomMod.PLAYER_NOT_FOUND);
       } else {
    	   sender_p.performCommand("cage " + p.getName() + " 101");
           sender_p.performCommand("say Verify please " + p.getName() + " via the shoutbox");
       }
	   return false;
    }
}