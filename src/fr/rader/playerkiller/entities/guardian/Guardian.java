package fr.rader.playerkiller.entities.guardian;

import fr.rader.playerkiller.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;

public class Guardian {

    private String team;
    private Player target;
    private IronGolem golem;

    public Guardian(String team, IronGolem golem) {
        this.team = team;
        this.golem = golem;

        golem.setCustomName("testing");
        golem.setCustomNameVisible(true);
    }

    public boolean isPlayerInSameTeam(Player player) {
        return player.getGameMode().equals(GameMode.SURVIVAL) &&
                Main.getInstance().getGamePlayerManager().getGamePlayer(player).getTeam().equals(this.team);
    }

    public IronGolem getGolem() {
        return golem;
    }

    public String getTeam() {
        return team;
    }
}
