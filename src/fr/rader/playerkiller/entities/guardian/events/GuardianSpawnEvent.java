package fr.rader.playerkiller.entities.guardian.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class GuardianSpawnEvent implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if(e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.BUILD_IRONGOLEM) {

        }
    }
}
