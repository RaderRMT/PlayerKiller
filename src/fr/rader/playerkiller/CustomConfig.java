package fr.rader.playerkiller;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public class CustomConfig {

    private final JavaPlugin plugin;
    private final String defaultConfig;

    private FileConfiguration config = null;
    private File configFile = null;

    public CustomConfig(JavaPlugin plugin, String defaultConfig) {
        this.plugin = plugin;
        this.defaultConfig = defaultConfig;
    }

    public void reloadConfig() {
        if(configFile == null) {
            configFile = new File(plugin.getDataFolder(), defaultConfig);
        }

        config = YamlConfiguration.loadConfiguration(configFile);

        // look for defaults in the jar
        InputStream resource = plugin.getResource(defaultConfig);
        if(resource != null) {
            Reader reader = new InputStreamReader(resource, StandardCharsets.UTF_8);

            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(reader);
            config.setDefaults(defaultConfig);
        } else {
            Bukkit.getLogger().log(Level.SEVERE, "Resource '" + defaultConfig + "' not found!");
        }
    }

    public FileConfiguration getConfig() {
        if(config == null) {
            reloadConfig();
        }

        return config;
    }

    public void saveConfig() {
        if(config == null || configFile == null) {
            return;
        }

        try {
            getConfig().save(configFile);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, e);
        }
    }
}
