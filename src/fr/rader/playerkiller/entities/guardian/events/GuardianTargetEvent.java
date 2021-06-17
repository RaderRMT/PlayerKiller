package fr.rader.playerkiller.entities.guardian.events;

import fr.rader.playerkiller.Main;
import fr.rader.playerkiller.entities.guardian.Guardian;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class GuardianTargetEvent implements Listener {

    @EventHandler
    public void onGolemTargetEvent(EntityTargetEvent e) {
        if(e.getTarget() instanceof Player) {
            Guardian guardian = Main.getInstance().getGuardianManager().getGuardian(e.getEntity());

            if(guardian == null) {
                return;
            }

            Player player = (Player) e.getTarget();
            if(guardian.isPlayerInSameTeam(player)) {
                e.setCancelled(true);
            }
        }
    }
}
