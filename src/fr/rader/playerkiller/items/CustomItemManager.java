package fr.rader.playerkiller.items;

import java.util.HashMap;

public class CustomItemManager {

    private final HashMap<String, CustomItem> customItems;

    public CustomItemManager() {
        this.customItems = new HashMap<>();
    }

    public void add(CustomItem item) {
        String type = item.getType();

        if(this.customItems.containsKey(type)) {
            throw new IllegalArgumentException("type " + type + " is already used by an item");
        }

        customItems.put(type, item);
    }

    public CustomItem get(String type) {
        return customItems.get(type);
    }
}
