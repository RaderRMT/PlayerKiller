package fr.rader.playerkiller;

import fr.rader.playerkiller.entities.guardian.events.GuardianSpawnEvent;
import fr.rader.playerkiller.events.crafting.CraftDisabler;
import fr.rader.playerkiller.events.crafting.CraftEvent;
import fr.rader.playerkiller.gamePlayers.GamePlayer;
import fr.rader.playerkiller.gamePlayers.GamePlayerManager;
import fr.rader.playerkiller.items.CustomItem;
import fr.rader.playerkiller.items.CustomItemManager;
import fr.rader.playerkiller.items.CustomItemType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    private final PluginManager pluginManager = Bukkit.getPluginManager();

    private final GamePlayerManager gamePlayerManager = new GamePlayerManager();
    private final CustomItemManager customItemManager = new CustomItemManager();
    private final CraftDisabler craftDisabler = new CraftDisabler();

    @Override
    public void onEnable() {
        instance = this;

        //////////////////////////////////////////////////////////////////////////////////
        // TODO:
        //  REMOVE ONCE USELESS
        //////////////////////////////////////////////////////////////////////////////////
        for(Player player : Bukkit.getOnlinePlayers()) {
            gamePlayerManager.add(new GamePlayer(player, "red"));
        }
        //////////////////////////////////////////////////////////////////////////////////

        disableCrafts();
        createCustomItems();

        //gamePlayerManager.find("red")[0].getPlayer().setItemOnCursor(customItemManager.get(CustomItemType.PK_SWORD).getItemStack());

        registerEvents();
    }

    private void disableCrafts() {
        // Beds cannot be crafted
        craftDisabler.disableCraft(Material.BLACK_BED);
        craftDisabler.disableCraft(Material.BLUE_BED);
        craftDisabler.disableCraft(Material.BROWN_BED);
        craftDisabler.disableCraft(Material.CYAN_BED);
        craftDisabler.disableCraft(Material.GRAY_BED);
        craftDisabler.disableCraft(Material.GREEN_BED);
        craftDisabler.disableCraft(Material.LIGHT_BLUE_BED);
        craftDisabler.disableCraft(Material.LIGHT_GRAY_BED);
        craftDisabler.disableCraft(Material.LIME_BED);
        craftDisabler.disableCraft(Material.MAGENTA_BED);
        craftDisabler.disableCraft(Material.ORANGE_BED);
        craftDisabler.disableCraft(Material.PINK_BED);
        craftDisabler.disableCraft(Material.PURPLE_BED);
        craftDisabler.disableCraft(Material.RED_BED);
        craftDisabler.disableCraft(Material.WHITE_BED);
        craftDisabler.disableCraft(Material.YELLOW_BED);

        // Enchanting tables cannot be crafted too
        craftDisabler.disableCraft(Material.ENCHANTING_TABLE);
    }

    private void createCustomItems() {
        // todo:
        //  find a better way to add custom items because this is a mess
        customItemManager.add(CustomItemType.PK_SWORD, new CustomItem(Material.DIAMOND_SWORD, "§6§lPlayer Killer", "§fBy using this item on a player,", "§fyou can lock them half a heart"));
        customItemManager.add(CustomItemType.PK_SHIELD, new CustomItem(Material.SHIELD, "§6§lPlayer Killer Shield", "§fBy having this item equipped,", "§fyou can avoid loosing half a heart"));
        customItemManager.add(CustomItemType.PK_HELMET, new CustomItem(Material.DIAMOND_HELMET, "§6§lPlayer Killer Helmet", "§fBy having this item equipped,", "§fyou can avoid loosing half a heart"));
        customItemManager.add(CustomItemType.PK_CHESTPLATE, new CustomItem(Material.DIAMOND_CHESTPLATE, "§6§lPlayer Killer Chestplate", "§fBy having this item equipped,", "§fyou can avoid loosing half a heart"));
        customItemManager.add(CustomItemType.PK_LEGGINGS, new CustomItem(Material.DIAMOND_LEGGINGS, "§6§lPlayer Killer Leggings", "§fBy having this item equipped,", "§fyou can avoid loosing half a heart"));
        customItemManager.add(CustomItemType.PK_BOOTS, new CustomItem(Material.DIAMOND_BOOTS, "§6§lPlayer Killer Boots", "§fBy having this item equipped,", "§fyou can avoid loosing half a heart"));
    }

    private void registerEvents() {
        pluginManager.registerEvents(new CraftEvent(), this);

        // Entity Events
        pluginManager.registerEvents(new GuardianSpawnEvent(), this);

        // Player Events
    }

    public static Main getInstance() {
        return instance;
    }

    public CraftDisabler getCraftDisabler() {
        return craftDisabler;
    }

    public GamePlayerManager getGamePlayerManager() {
        return gamePlayerManager;
    }

    public CustomItemManager getCustomItemManager() {
        return customItemManager;
    }
}
