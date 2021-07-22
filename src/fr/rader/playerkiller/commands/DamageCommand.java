package fr.rader.playerkiller.commands;

import fr.rader.playerkiller.Main;
import fr.rader.playerkiller.gamePlayers.GamePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DamageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            GamePlayer gamePlayer = Main.getInstance().getGamePlayerManager().getGamePlayer(player);

            gamePlayer.reduceMaxHealth();
        }

        return false;
    }
}
