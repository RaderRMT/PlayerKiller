package fr.rader.playerkiller.entities.guardian;

import fr.rader.playerkiller.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
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

    public void tick() {
        if(this.target == null) {
            for(Entity entity : this.golem.getNearbyEntities(20, 20, 20)) {
                if(entity instanceof Player) {
                    Player player = ((Player) entity).getPlayer();

                    if(this.isPlayerInSameTeam(player)) {
                        this.target = null;
                        this.golem.setTarget(null);
                    } else {
                        if(!player.isDead()) {
                            this.golem.damage(0, player);
                            this.golem.setTarget(player);
                            this.target = player;

                            return;
                        }
                    }
                }
            }
        } else {
            this.golem.setTarget(this.target);

            if(this.target.isDead()) {
                this.target = null;
                this.golem.setTarget(null);
            }
        }
    }

    private boolean isPlayerInSameTeam(Player player) {
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
