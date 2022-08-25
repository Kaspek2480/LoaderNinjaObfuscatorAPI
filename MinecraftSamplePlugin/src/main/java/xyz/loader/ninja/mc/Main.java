package xyz.loader.ninja.mc;

import com.loader.ninja.NativeBridge;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.loader.ninja.mc.command.BasicCommand;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends JavaPlugin {

    private static final Path TITANAXE_PATH = Paths.get(".titanaxe");
    private static final Path NATIVE_PATH = Paths.get("game", "bin", "libs", "libtestEnv.so");

    @Override
    public void onEnable() {
        if (Files.exists(TITANAXE_PATH)) {
            System.out.println("Titanaxe test env mode test enabled");
            System.load(NATIVE_PATH.toAbsolutePath().toString());
        }

        if (!NativeBridge.initialized()) {
            getLogger().warning("Native bridge not initialized");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("test").setExecutor(new BasicCommand());
        getLogger().info("Plugin zostal wlaczony!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin zostal wylaczony!");
    }
}
