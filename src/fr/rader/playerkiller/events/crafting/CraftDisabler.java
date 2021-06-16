package fr.rader.playerkiller.events.crafting;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class CraftDisabler {

    private final List<Material> disabledCrafts;

    public CraftDisabler() {
        this.disabledCrafts = new ArrayList<>();
    }

    public void disableCraft(Material material) {
        disabledCrafts.add(material);
    }

    public List<Material> getDisabledCrafts() {
        return disabledCrafts;
    }

    public boolean isDisabled(Material material) {
        return disabledCrafts.contains(material);
    }
}
