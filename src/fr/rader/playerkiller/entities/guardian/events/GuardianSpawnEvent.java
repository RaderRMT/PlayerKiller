package fr.rader.playerkiller.entities.guardian.events;

import fr.rader.playerkiller.Main;
import fr.rader.playerkiller.entities.guardian.Guardian;
import fr.rader.playerkiller.gamePlayers.GamePlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class GuardianSpawnEvent implements Listener {

    @EventHandler
    public void onPlayerBuildGolem(BlockPlaceEvent e) {
        if(e.getBlockPlaced().getType().equals(Material.CARVED_PUMPKIN)) {
            Main main = Main.getInstance();

            Location pumpkinLocation = e.getBlockPlaced().getLocation();
            if(checkVertical(pumpkinLocation)) {
                if(checkArms(pumpkinLocation)) {
                    GamePlayer gamePlayer = main.getGamePlayerManager().getGamePlayer(e.getPlayer());
                    String playerTeam = gamePlayer.getTeam();

                    if(main.getGuardianManager().guardiansInTeam(playerTeam) < 3) {
                        removeGolem(pumpkinLocation);
                        e.getPlayer().getInventory().removeItem(new ItemStack(Material.CARVED_PUMPKIN, 1));

                        main.getGuardianManager().add(
                                new Guardian(playerTeam,
                                        spawnIronGolem(pumpkinLocation))
                        );
                    } else {
                        e.setCancelled(true);
                        gamePlayer.getPlayer().sendMessage("You spawned too many golems!");
                    }
                }
            }
        }
    }

    private IronGolem spawnIronGolem(Location loc) {
        return (IronGolem) loc.getWorld().spawnEntity(
                new Location(loc.getWorld(),
                    loc.getBlockX() + .5f,
                    loc.getBlockY() - 2f,
                    loc.getBlockZ() + .5f),
                EntityType.IRON_GOLEM);
    }

    private void removeGolem(Location loc) {
        removeBlock(loc, 0, 0, 0);
        removeBlock(loc, 0, -1, 0);
        removeBlock(loc, 0, -2, 0);

        if(checkBlock(loc, 1, -1, 0)) {
            removeBlock(loc, 1, -1, 0);
            removeBlock(loc, -1, -1, 0);
        } else if(checkBlock(loc, 0, -1, 1)) {
            removeBlock(loc, 0, -1, 1);
            removeBlock(loc, 0, -1, -1);
        }
    }

    private boolean checkVertical(Location loc) {
        return checkBlock(loc, 0, -1, 0) && checkBlock(loc, 0, -2, 0);
    }

    private boolean checkArms(Location loc) {
        return (checkBlock(loc, 1, -1, 0) && checkBlock(loc, -1, -1, 0))
                | (checkBlock(loc, 0, -1, 1) && checkBlock(loc, 0, -1, -1));
    }

    private boolean checkBlock(Location loc, int xOffset, int yOffset, int zOffset) {
        Location block = new Location(loc.getWorld(),
                loc.getBlockX() + xOffset,
                loc.getBlockY() + yOffset,
                loc.getBlockZ() + zOffset);

        return block.getBlock().getType().equals(Material.IRON_BLOCK);
    }

    private void removeBlock(Location loc, int xOffset, int yOffset, int zOffset) {
        new Location(loc.getWorld(),
                loc.getBlockX() + xOffset,
                loc.getBlockY() + yOffset,
                loc.getBlockZ() + zOffset).getBlock().setType(Material.AIR);
    }
}
