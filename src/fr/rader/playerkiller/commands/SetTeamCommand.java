package fr.rader.playerkiller.commands;

import fr.rader.playerkiller.Main;
import fr.rader.playerkiller.gamePlayers.GamePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetTeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            if(args.length != 1) {
                sender.sendMessage("too many/few arguments");
            } else {
                GamePlayer gamePlayer = Main.getInstance().getGamePlayerManager().getGamePlayer((Player) sender);

                gamePlayer.setTeam(args[0]);

                sender.sendMessage("Team set to '" + args[0] + "'");
            }
        }

        return false;
    }
}
