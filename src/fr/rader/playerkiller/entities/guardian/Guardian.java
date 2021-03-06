package fr.rader.playerkiller.entities.guardian;

import fr.rader.playerkiller.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;

public class Guardian {

    private String team;
    private IronGolem golem;

    public Guardian(String team, IronGolem golem) {
        this.team = team;
        this.golem = golem;

        golem.setCustomName("Team: " + team);
        golem.setCustomNameVisible(true);
    }

    public boolean isPlayerInSameTeam(Player player) {
        if(!player.getGameMode().equals(GameMode.SURVIVAL)) {
            return true;
        }

        return Main.getInstance().getGamePlayerManager().getGamePlayer(player).getTeam().equals(this.team);
    }

    public IronGolem getGolem() {
        return golem;
    }

    public String getTeam() {
        return team;
    }
}
