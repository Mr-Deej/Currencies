package dansplugins.currencies;

import dansplugins.currencies.bstats.Metrics;
import dansplugins.currencies.managers.ConfigManager;
import dansplugins.currencies.managers.StorageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Currencies extends JavaPlugin {

    private static Currencies instance;

    private final String version = "v1.1";

    public static Currencies getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // bStats
        int pluginId = 12810;
        Metrics metrics = new Metrics(this, pluginId);

        // create/load config
        if (!(new File("./plugins/Currencies/config.yml").exists())) {
            ConfigManager.getInstance().saveMissingConfigDefaultsIfNotPresent();
        }
        else {
            // pre load compatibility checks
            if (isVersionMismatched()) {
                ConfigManager.getInstance().saveMissingConfigDefaultsIfNotPresent();
            }
            reloadConfig();
        }

        EventRegistry.getInstance().registerEvents();

        StorageManager.getInstance().load();

        Scheduler.getInstance().scheduleAutosave();
    }

    @Override
    public void onDisable() {
        StorageManager.getInstance().save();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CommandInterpreter commandInterpreter = new CommandInterpreter();
        return commandInterpreter.interpretCommand(sender, label, args);
    }

    public String getVersion() {
        return version;
    }

    public boolean isDebugEnabled() {
        return ConfigManager.getInstance().getBoolean("debugMode");
    }

    private boolean isVersionMismatched() {
        return !getConfig().getString("version").equalsIgnoreCase(getVersion());
    }
}