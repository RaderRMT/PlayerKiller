package fr.rader.playerkiller.items;

import java.util.HashMap;

public class CustomItemManager {

    private final HashMap<CustomItemType, CustomItem> customItems;

    public CustomItemManager() {
        customItems = new HashMap<>();
    }

    public void add(CustomItemType type, CustomItem item) {
        this.customItems.put(type, item);
    }

    public CustomItem get(CustomItemType type) {
        return customItems.get(type);
    }

    // todo: make the match
    public boolean match(CustomItem item1, CustomItem item2) {
        return false;
    }
}
