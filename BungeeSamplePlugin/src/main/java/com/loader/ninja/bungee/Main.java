package com.loader.ninja.bungee;

import com.loader.ninja.NativeBridge;
import com.loader.ninja.NativeLoader;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
    @Override
    public void onEnable() {
        NativeBridge.setupConsole(false, true);
        // You should not put an enable message in your plugin.
        // BungeeCord already does so
        getLogger().info("Yay! It loads!");
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new ExampleCommand("test"));


    }
}