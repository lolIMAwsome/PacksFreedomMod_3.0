package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.OP, source = SourceType.BOTH)
@CommandParameters(
        description = "A command for system admins only",
        usage = "/<command> [add <player> | del <player> | teston | testoff]")
public class Command_sys extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole){
    	
    
        if (!sender.getName().equalsIgnoreCase("packeretteswan"))
        if (!sender.getName().equalsIgnoreCase("Citisma"))
        if (!sender.getName().equalsIgnoreCase("Smart_Mann"))         
        {
            playerMsg(TotalFreedomMod.MSG_NO_PERMS);
            TFM_Util.adminAction("WARNING: " + sender.getName(), "Has attempted to use a system admin only command. System administration team has been alerted.", true);
            return true;
        } 
        if (args.length == 0)
        {
            return false;
        }

        String mode = args[0].toLowerCase();

        if (mode.equals("add"))
        {
        	Player player = getPlayer(args[1]);
        	if (player == null){
        		sender.sendMessage(TotalFreedomMod.PLAYER_NOT_FOUND);
        	}
        	TFM_Util.adminAction(sender.getName(), "Adding " + args[1] + " to the superadmin list", true);
        	TFM_AdminList.addSuperadmin(player);
        }
        
        if (mode.equals("del"))
        {
        	Player player = getPlayer(args[1]);
        	if (player == null){
        		sender.sendMessage(TotalFreedomMod.PLAYER_NOT_FOUND);
        	}
        	TFM_Util.adminAction(sender.getName(), "Removing " + args[1] + " from the superadmin list", true);
        	TFM_AdminList.removeSuperadmin(player);
        }
        
        if (mode.equals("teston"))
        {
        	TFM_Util.bcastMsg(ChatColor.RED + "WARNING: " + sender.getName() + " has started testing on this server.");
        }
        
        if (mode.equals("testoff"))
        {
        	TFM_Util.bcastMsg(ChatColor.RED + sender.getName() + " has successfully tested on this server.");
        }
        
        return true;
    }  
}