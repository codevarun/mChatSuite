package in.mDev.MiracleM4n.mChatSuite.spout.commands;

import in.mDev.MiracleM4n.mChatSuite.spout.mChatSuite;

public class PMChatInviteCommand {
    mChatSuite plugin;

    public PMChatInviteCommand(mChatSuite plugin) {
        this.plugin = plugin;
    }

    //TODO Wait for implementation
    /*
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName();

        if (!(sender instanceof Player)) {
            sender.sendMessage(formatPMessage(plugin.getAPI().addColour("Console's can't send PM's.")));
            return true;
        }

        Player player = (Player) sender;
        String pName = player.getName();
        String world = player.getWorld().getName();

        if (commandName.equalsIgnoreCase("pmchatinvite")) {
            if (args.length < 1)
                return false;

            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "mchat.pm.invite")) {
                player.sendMessage(formatPMessage(plugin.getAPI().addColour("You are not allowed to use Invite functions.")));
                return true;
            }

            Player recipient = plugin.getGame().getPlayer(args[0]);
            String rName = recipient.getName();
            String rWorld = recipient.getWorld().getName();

            if (recipient == null) {
                player.sendMessage(formatPNF(args[0]));
                return true;
            }

            if (plugin.getInvite.get(rName) != null) {
                player.sendMessage(formatPMessage(plugin.getAPI().addColour("&5'&4" + plugin.getAPI().ParsePlayerName(rName, rWorld) + "&5'&4 Already has a Convo request.")));
                return true;
            } else {
                plugin.getInvite.put(rName, pName);
                player.sendMessage(formatPMessage(plugin.getAPI().addColour("You have invited &5'&4" + plugin.getAPI().ParsePlayerName(rName, rWorld) + "&5'&4 to have a Convo.")));
                recipient.sendMessage(formatPMessage(plugin.getAPI().addColour("You have been invited to a Convo by &5'&4" + plugin.getAPI().ParsePlayerName(pName, world) + "&5'&4 use /pmchataccept to accept.")));
                return true;
            }
        }

        return false;
    }

    String formatPMessage(String message) {
        return (plugin.getAPI().addColour("&4[" + (plugin.pdfFile.getName()) + "] " + message));
    }

    String formatPNF(String playerNotFound) {
        return (plugin.getAPI().addColour("&4[" + (plugin.pdfFile.getName()) + "]" + " Player &e" + playerNotFound + " &4not found."));
    }
    */
}