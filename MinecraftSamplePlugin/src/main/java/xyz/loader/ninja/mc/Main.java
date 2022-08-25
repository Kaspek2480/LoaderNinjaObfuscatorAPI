package xyz.loader.ninja.mc;

import com.loader.ninja.NativeBridge;
import net.md_5.bungee.api.ProxyServer;
import org.apache.commons.codec.binary.Hex;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Vector;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin zostal wlaczony!");
        if (new File(".titanaxe").exists()) {
            System.out.println("Titanaxe test env mode test enabled");
            System.load("/game/bin/libs/libtestEnv.so");
        } else {
            new NativeBridge();
        }

        getCommand("test").setExecutor(new BasicCommand());
//        Bukkit.getServer().getPluginManager().disablePlugin(this);
//        NativeBridge.bakeCake(5, "Cake".getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin zostal wylaczony!");
    }


}
