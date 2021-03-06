package in.mDev.MiracleM4n.mChatSuite.commands;

import in.mDev.MiracleM4n.mChatSuite.mChatSuite;
import in.mDev.MiracleM4n.mChatSuite.types.LocaleType;
import in.mDev.MiracleM4n.mChatSuite.util.Messanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhoCommand implements CommandExecutor {
    mChatSuite plugin;

    public WhoCommand(mChatSuite instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName();

        if (cmd.equalsIgnoreCase("mchatwho")) {
            if (args.length > 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "mchat.who")) {
                        Messanger.sendMessage(sender, plugin.getLocale().getOption(LocaleType.NO_PERMS).replace("%permission%", "mchat.who"));
                        return true;
                    }
                }

                if (plugin.getServer().getPlayer(args[0]) == null) {
                    sender.sendMessage(formatPNF(args[0]));
                    return true;
                } else {
                    Player receiver = plugin.getServer().getPlayer(args[0]);
                    formatWho(sender, receiver);
                    return true;
                }
            }
        }

        return false;
    }

    void formatWho(CommandSender sender, Player recipient) {
        String recipientName = plugin.getParser().parsePlayerName(recipient.getName(), recipient.getWorld().getName());
        Integer locX = (int) recipient.getLocation().getX();
        Integer locY = (int) recipient.getLocation().getY();
        Integer locZ = (int) recipient.getLocation().getZ();
        String loc = ("X: " + locX + ", " + "Y: " + locY + ", " + "Z: " + locZ);
        String world = recipient.getWorld().getName();

        Messanger.sendColouredMessage(sender, "Player Name: " + recipient.getName());
        Messanger.sendColouredMessage(sender, "Display Name: " + recipient.getDisplayName());
        Messanger.sendColouredMessage(sender, "Formatted Name: " + recipientName);
        Messanger.sendColouredMessage(sender, "Player's Location: [ " + loc + " ]");
        Messanger.sendColouredMessage(sender, "Player's World: " + world);
        Messanger.sendColouredMessage(sender, "Player's Health: " + plugin.getAPI().createHealthBar(recipient) + " " + recipient.getHealth() + "/20");
        Messanger.sendColouredMessage(sender, "Player's IP: " + recipient.getAddress().getHostString());
        Messanger.sendColouredMessage(sender, "Current Item: " + recipient.getItemInHand().getType().name());
        Messanger.sendColouredMessage(sender, "Entity ID: #" + recipient.getEntityId());
    }

    String formatPNF(String playerNotFound) {
        return Messanger.format("&4Player &e'" + playerNotFound + "'&4 not Found.");
    }
}
