package fr.rader.playerkiller.gamePlayers;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class GamePlayer {

    private final Player player;

    private byte maxHealth;
    private String team;

    public GamePlayer(Player player, String team) {
        this.player = player;
        this.team = team;
        this.maxHealth = 20;
    }

    // reduce player's max health by 1
    public void reduceMaxHealth() {
        this.maxHealth--;

        if(this.maxHealth > 0) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        } else {
            player.setHealth(0);
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

    public byte getMaxHealth() {
        return maxHealth;
    }
}
