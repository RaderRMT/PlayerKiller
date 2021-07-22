package fr.rader.playerkiller.entities.guardian;

import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GuardianManager {

    private final List<Guardian> guardians;

    public GuardianManager() {
        this.guardians = new ArrayList<>();
    }

    public void add(Guardian guardian) {
        guardians.add(guardian);
    }

    public int guardiansInTeam(String team) {
        return getGuardiansInTeam(team).size();
    }

    public List<Guardian> getGuardiansInTeam(String team) {
        List<Guardian> out = new ArrayList<>();

        for(Guardian guardian : guardians) {
            if(guardian.getTeam().equals(team)) {
                out.add(guardian);
            }
        }

        return out;
    }

    public void tickGuardians() {
        for(Guardian guardian : guardians) {
            IronGolem ironGolem = guardian.getGolem();

            // check for each guardians if an enemy is 20 blocks away
            // only if they do not have a target
            if(ironGolem.getTarget() == null) {
                for(Entity entity : ironGolem.getNearbyEntities(20, 20, 20)) {
                    // look if the entity is a player
                    // a guardian will never attack any entity that isn't a player
                    if(entity instanceof Player) {
                        Player player = (Player) entity;

                        // attack only players that are not in the same team as the guardian
                        if(!guardian.isPlayerInSameTeam(player)) {
                            ironGolem.setTarget(player);
                            return;
                        }
                    }
                }
            } else {
                // look if the guardian has a target, and that target is a player
                if(ironGolem.getTarget() instanceof Player) {
                    Player target = (Player) ironGolem.getTarget();

                    // we look if the player is dead.
                    // if it is, then we set the guardian's target to null
                    if(target.isDead()) {
                        ironGolem.setTarget(null);
                    }
                }
            }

            // todo: check for each guardians if they left the "town hall" limit
            //  if they do, we remove the target.
        }
    }

    public Guardian getGuardian(Entity entity) {
        for(Guardian guardian : guardians) {
            if(guardian.getGolem().equals(entity)) {
                return guardian;
            }
        }

        return null;
    }

    public void removeGuardian(Guardian guardian) {
        guardians.remove(guardian);
    }
}
