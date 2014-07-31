package me.StevenLawson.TotalFreedomMod;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.DEVELOPERS;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum TFM_PlayerRank
{
    DEVELOPER("a " + ChatColor.DARK_PURPLE + "Developer", ChatColor.DARK_PURPLE + "[Dev]"),
    IMPOSTOR("an " + ChatColor.YELLOW + ChatColor.UNDERLINE + "Impostor", ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + "[IMP]"),
    NON_OP("a " + ChatColor.GREEN + "Non-OP", ChatColor.GREEN.toString()),
    OP("an " + ChatColor.RED + "OP", ChatColor.RED + "[OP]"),
    SUPER("a " + ChatColor.GOLD + "Super Admin", ChatColor.GOLD + "[SA]"),
    TELNET("a " + ChatColor.DARK_GREEN + "Super Telnet Admin", ChatColor.DARK_GREEN + "[STA]"),
    SENIOR("a " + ChatColor.LIGHT_PURPLE + "Senior Admin", ChatColor.LIGHT_PURPLE + "[SrA]"),
    OWNER("the " + ChatColor.BLUE + "Owner", ChatColor.BLUE + "[Owner]"),
    CONSOLE("The " + ChatColor.BLUE + "Console", ChatColor.BLUE + "[Console]"),
    EXECUTIVE("an " + ChatColor.RED + "Executive", ChatColor.RED + "[Executive]"),
    SYSADMIN("a " + ChatColor.DARK_GREEN + "System Admin", ChatColor.DARK_GREEN + "[Sys-Admin]"),
    MASTERBUILDER("a " + ChatColor.DARK_BLUE + "MasterBuilder", ChatColor.DARK_BLUE + "[MasterBuilder]"),
    HSRA("an " + ChatColor.LIGHT_PURPLE + "Honorary Senior Admin", ChatColor.LIGHT_PURPLE + "[Honorary SrA]"),
    COOWNER("the ", ChatColor.BLUE + "Co Owner!", ChatColor.BLUE + "[Co Owner]");
    private String prefix;
    private String loginMessage;

    private TFM_PlayerRank(String loginMessage, String prefix)
    {
        this.loginMessage = loginMessage;
        this.prefix = prefix;
    }

    public static String getLoginMessage(CommandSender sender)
    {
        // Handle console
        if (!(sender instanceof Player))
        {
            return fromSender(sender).getLoginMessage();
        }

        // Handle admins
        final TFM_Admin entry = TFM_AdminList.getEntry((Player) sender);
        if (entry == null)
        {
            // Player is not an admin
            return fromSender(sender).getLoginMessage();
        }

        // Custom login message
        final String loginMessage = entry.getCustomLoginMessage();

        if (loginMessage == null || loginMessage.isEmpty())
        {
            return fromSender(sender).getLoginMessage();
        }

        return ChatColor.translateAlternateColorCodes('&', loginMessage);
    }

    public static TFM_PlayerRank fromSender(CommandSender sender)
    {
        if (!(sender instanceof Player))
        {
            return CONSOLE;
        }

        if (TFM_AdminList.isAdminImpostor((Player) sender))
        {
            return IMPOSTOR;
        }

        if (DEVELOPERS.contains(sender.getName()))
        {
            return DEVELOPER;
        }


        final TFM_Admin entry = TFM_AdminList.getEntry((Player) sender);

        final TFM_PlayerRank rank;

        if (entry != null && entry.isActivated())
        {
            if (sender.getName().equals("packeretteswan"))
            {
                return SYSADMIN;
            }
             if (sender.getName().equals("taahanis"))
            {
                return COOWNER;
            }
             if (sender.getName().equals("Citisma"))
            {
                return SYSADMIN;
            }        
              if (sender.getName().equals("RedSea11001"))
            {
                return MASTERBUILDER;
            }     
              if (sender.getName().equals("Elephants111"))
            {
                return HSRA;
            }
              if (sender.getName().equals("amanshafeeq123"))
            {
                return MASTERBUILDER;
            }  
              if (sender.getName().equals("Jason1712445"))
            {
                return DEVELOPER;
            }
              if (sender.getName().equals("ryandw11"))
            {
                return MASTERBUILDER;
            }
              if (sender.getName().equals("boomy46"))
            {
                return DEVELOPER;
            } 
              if (sender.getName().equals("Smart_Mann"))
            {
                return SYSADMIN;
            }
            if (sender.getName().equals("DDQ888"))
            {
                return EXECUTIVE;
            }
              
              
            if (entry.isSeniorAdmin())
            {
                rank = SENIOR;
            }
            else if (entry.isTelnetAdmin())
            {
                rank = TELNET;
            }
            else
            {
                rank = SUPER;
            }
        }
        else
        {
            if (sender.isOp())
            {
                rank = OP;
            }
            else
            {
                rank = NON_OP;
            }

        }
        return rank;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public String getLoginMessage()
    {
        return loginMessage;
    }
}
