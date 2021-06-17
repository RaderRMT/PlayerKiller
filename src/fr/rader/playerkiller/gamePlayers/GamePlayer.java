package fr.rader.playerkiller.gamePlayers;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class GamePlayer {

    private String team;
    private Player player;
    private byte health;

    public GamePlayer(Player player, String team) {
        this.player = player;
        this.team = team;
        this.health = 20;
    }

    public void damage(int damagePoints, Player damager) {
        this.health -= damagePoints;

        if(this.health > 0) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        } else {
            player.damage(1, damager);
        }
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isDead() {
        return player.isDead();
    }

    public byte getHealth() {
        return health;
    }
}
