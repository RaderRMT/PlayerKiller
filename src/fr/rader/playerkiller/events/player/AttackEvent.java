package fr.rader.playerkiller.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class AttackEvent implements Listener {

    @EventHandler
    public void onAttackEvent(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {
            Player damager = ((Player) e.getDamager()).getPlayer();
            ItemStack damagerItem = damager.getInventory().getItemInMainHand();

            if(e.getEntity() instanceof Player) {

            } else {
                // cancel the event if the player is holder the player killer sword
            }
        }
    }
}
