package fr.rader.playerkiller.events.crafting;

import fr.rader.playerkiller.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftEvent implements Listener {

    @EventHandler
    public void onCraftEvent(CraftItemEvent e) {
        CraftDisabler disabler = Main.getInstance().getCraftDisabler();
        Material craftedMaterial = e.getRecipe().getResult().getType();

        if(disabler.isDisabled(craftedMaterial)) {
            e.getWhoClicked().sendMessage("You cannot craft this item!");
            e.setCancelled(true);
        }
    }
}
