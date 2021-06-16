package fr.rader.playerkiller.entities.guardian;

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
        List<Guardian> deadGuardians = new ArrayList<>();

        for(Guardian guardian : guardians) {
            if(guardian.getGolem().isDead()) {
                deadGuardians.add(guardian);
            } else {
                guardian.tick();
            }
        }

        for(Guardian guardian : deadGuardians) {
            guardians.remove(guardian);
        }
    }
}
