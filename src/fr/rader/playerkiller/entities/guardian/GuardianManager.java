package fr.rader.playerkiller.entities.guardian;

import org.bukkit.entity.Entity;
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
            // check for each guardians if an enemy is 20 blocks away
            // only if they do not have a target
            if(guardian.getGolem().getTarget() == null) {
                for(Entity entity : guardian.getGolem().getNearbyEntities(20, 20, 20)) {
                    if(entity instanceof Player) {
                        Player player = (Player) entity;

                        if(!guardian.isPlayerInSameTeam(player)) {
                            guardian.getGolem().setTarget(player);
                            return;
                        }
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
