package fr.rader.playerkiller;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
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
    private final File configFile;

    public CustomConfig(JavaPlugin plugin, String defaultConfig) {
        this.plugin = plugin;
        this.defaultConfig = defaultConfig;

        this.configFile = new File(plugin.getDataFolder(), defaultConfig);
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);

        try {
            if(configFile.exists()) {
                // load the config if it already exists
                // in the plugin's config folder
                config.load(configFile);
            } else {
                // load the config from the jar file if the config
                // does not exist in the plugin's config folder
                InputStream resource = plugin.getResource(defaultConfig);
                if(resource != null) {
                    config.load(new InputStreamReader(resource, StandardCharsets.UTF_8));
                } else {
                    Bukkit.getLogger().log(Level.SEVERE, "Resource '" + defaultConfig + "' not found!");
                }
            }
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
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
