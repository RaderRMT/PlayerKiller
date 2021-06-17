package fr.rader.playerkiller.entities.guardian.events;

import fr.rader.playerkiller.Main;
import fr.rader.playerkiller.entities.guardian.Guardian;
import fr.rader.playerkiller.entities.guardian.GuardianManager;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class GuardianDeathEvent implements Listener {

    @EventHandler
    public void onGolemDeath(EntityDeathEvent e) {
        if(e.getEntity().getType().equals(EntityType.IRON_GOLEM)) {
            GuardianManager manager = Main.getInstance().getGuardianManager();
            Guardian guardian = manager.getGuardian(e.getEntity());

            if(guardian != null) {
                manager.removeGuardian(guardian);
            }
        }
    }
}
