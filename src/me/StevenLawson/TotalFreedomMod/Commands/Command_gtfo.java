package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.Bridge.TFM_WorldEditBridge;
import me.StevenLawson.TotalFreedomMod.TFM_Ban;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_RollbackManager;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import me.confuser.barapi.BarAPI;
import net.minecraft.util.org.apache.commons.lang3.ArrayUtils;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Makes someone GTFO (deop and ip ban by username).", usage = "/<command> <partialname> <reason>")
public class Command_gtfo extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            return false;
        }

        

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            playerMsg(TotalFreedomMod.PLAYER_NOT_FOUND, ChatColor.RED);
            return true;
        }

        String reason = null;
        if (args.length >= 2)
        {
            reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        }

        TFM_Util.bcastMsg(player.getName() + " has been a VERY naughty, naughty boy.", ChatColor.RED);
        // Undo WorldEdits:
        try
        {
            server.dispatchCommand(sender, "gcmd " + player.getName() + " /undo 16");
        }
        catch (NoClassDefFoundError ex)
        {
        }

        // rollback
        TFM_RollbackManager.rollback(player.getName());

        // deop
        player.setOp(false);

        // set gamemode to survival:
        player.setGameMode(GameMode.SURVIVAL);

        // clear inventory:
        player.getInventory().clear();

        // strike with lightning effect:
        final Location targetPos = player.getLocation();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                final Location strike_pos = new Location(targetPos.getWorld(), targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                targetPos.getWorld().strikeLightning(strike_pos);
            }
        }

        // ban IP address:
        String ip = TFM_Util.getFuzzyIp(player.getAddress().getAddress().getHostAddress());
        TFM_Util.bcastMsg(String.format("%s - Banning: %s, IP: %s.", sender.getName(), player.getName(), ip), ChatColor.RED);
        TFM_Util.bcastMsg(ChatColor.RED +  (reason != null ? ("Reason: " + ChatColor.YELLOW + reason) : ""));

        TFM_BanManager.addIpBan(new TFM_Ban(ip, player.getName(), sender.getName(), null, reason));

        // ban username:
        TFM_BanManager.addUuidBan(new TFM_Ban(player.getUniqueId(), player.getName(), sender.getName(), null, reason));

        // kick Player:
        player.kickPlayer(ChatColor.RED + "GTFO" + "(" + sender.getName() + ")"+ (reason != null ? ("\nReason: " + ChatColor.YELLOW + reason) : ""));

            String message = StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " ");
            BarAPI.setMessage(ChatColor.RED + sender.getName() + ChatColor.RED + " has banned " + ChatColor.RED + player.getName() + ChatColor.RED, 60);
        return true;
    }
}
