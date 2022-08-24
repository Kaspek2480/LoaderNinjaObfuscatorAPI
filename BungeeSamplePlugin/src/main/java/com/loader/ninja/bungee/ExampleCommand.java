package com.loader.ninja.bungee;

import com.loader.ninja.NativeBridge;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class ExampleCommand extends Command {
    public ExampleCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        NativeBridge.setupConsole(false, true);
        System.out.println("Console setup..");
    }
}
