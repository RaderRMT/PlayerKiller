package fr.rader.playerkiller;

import com.google.gson.Gson;
import fr.rader.playerkiller.commands.DamageCommand;
import fr.rader.playerkiller.commands.SetTeamCommand;
import fr.rader.playerkiller.entities.guardian.GuardianManager;
import fr.rader.playerkiller.entities.guardian.events.GuardianDeathEvent;
import fr.rader.playerkiller.entities.guardian.events.GuardianSpawnEvent;
import fr.rader.playerkiller.entities.guardian.events.GuardianTargetEvent;
import fr.rader.playerkiller.events.crafting.CraftDisabler;
import fr.rader.playerkiller.events.crafting.CraftEvent;
import fr.rader.playerkiller.events.player.PlayerAttackEvent;
import fr.rader.playerkiller.gamePlayers.GamePlayer;
import fr.rader.playerkiller.gamePlayers.GamePlayerManager;
import fr.rader.playerkiller.items.CustomItem;
import fr.rader.playerkiller.items.CustomItemManager;
import fr.rader.playerkiller.items.CustomItemType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    private final PluginManager pluginManager = Bukkit.getPluginManager();

    // Managers:
    private final GamePlayerManager gamePlayerManager = new GamePlayerManager();
    private final CustomItemManager customItemManager = new CustomItemManager();
    private final GuardianManager guardianManager = new GuardianManager();
    private final CraftDisabler craftDisabler = new CraftDisabler();

    // Configs:
    private CustomConfig customItemConfig;

    @Override
    public void onEnable() {
        instance = this;

        //////////////////////////////////////////////////////////////////////////////////
        // TODO:
        //  REMOVE ONCE USELESS
        //////////////////////////////////////////////////////////////////////////////////
        for(Player player : Bukkit.getOnlinePlayers()) {
            gamePlayerManager.add(new GamePlayer(player, "red"));
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        }
        //////////////////////////////////////////////////////////////////////////////////

        prepareConfigs();
        disableCrafts();
        createCustomItems();

        //gamePlayerManager.getGamePlayersInTeam("red").get(0).getPlayer().getInventory().setItem(0, customItemManager.get(CustomItemType.PK_SWORD).getItemStack());

        registerCommands();
        registerEvents();

        getServer().getLogger().info("[PlayerKiller] PlayerKiller v" + getDescription().getVersion() + " enabled!");

        getServer().getScheduler().scheduleSyncRepeatingTask(this, Tick::tick, 0L, 1L);
    }

    private void prepareConfigs() {
        customItemConfig = new CustomConfig(this, "custom_items.yml");
        customItemConfig.reloadConfig();
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
        customItemManager.add(new CustomItem(CustomItemType.PK_SWORD));
        customItemManager.add(new CustomItem(CustomItemType.PK_SHIELD));
        customItemManager.add(new CustomItem(CustomItemType.PK_HELMET));
        customItemManager.add(new CustomItem(CustomItemType.PK_CHESTPLATE));
        customItemManager.add(new CustomItem(CustomItemType.PK_LEGGINGS));
        customItemManager.add(new CustomItem(CustomItemType.PK_BOOTS));
    }

    private void registerCommands() {
        this.getCommand("setteam").setExecutor(new SetTeamCommand());
        this.getCommand("damage").setExecutor(new DamageCommand());
    }

    private void registerEvents() {
        // Crafting Events
        pluginManager.registerEvents(new CraftEvent(), this);

        // Entity Events
        pluginManager.registerEvents(new GuardianSpawnEvent(), this);
        pluginManager.registerEvents(new GuardianTargetEvent(), this);
        pluginManager.registerEvents(new GuardianDeathEvent(), this);

        // Player Events
        pluginManager.registerEvents(new PlayerAttackEvent(), this);
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

    public GuardianManager getGuardianManager() {
        return guardianManager;
    }

    public CustomItemManager getCustomItemManager() {
        return customItemManager;
    }

    public CustomConfig getCustomItemConfig() {
        return customItemConfig;
    }
}
