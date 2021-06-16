package fr.rader.playerkiller.gamePlayers;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GamePlayerManager {

    private final List<GamePlayer> gamePlayers;

    public GamePlayerManager() {
        this.gamePlayers = new ArrayList<>();
    }

    public void add(GamePlayer gamePlayer) {
        gamePlayers.add(gamePlayer);
    }

    public GamePlayer getGamePlayer(Player player) {
        for(GamePlayer gamePlayer : gamePlayers) {
            if(gamePlayer.getPlayer().equals(player)) {
                return gamePlayer;
            }
        }

        return null;
    }

    /**
     * Get the GamePlayer from players with a certain team
     * @param team Team of the players
     * @return A list containing all players in the team
     */
    public List<GamePlayer> getGamePlayersInTeam(String team) {
        List<GamePlayer> out = new ArrayList<>();

        for(GamePlayer gamePlayer : gamePlayers) {
            if(gamePlayer.getTeam().equals(team)) {
                out.add(gamePlayer);
            }
        }

        return out;
    }
}
