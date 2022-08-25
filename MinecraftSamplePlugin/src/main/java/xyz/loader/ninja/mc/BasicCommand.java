package xyz.loader.ninja.mc;

import com.loader.ninja.NativeBridge;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BasicCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        byte[] bake = NativeBridge.bake(strings[0].getBytes(StandardCharsets.UTF_8), 5);
        commandSender.sendMessage(Arrays.toString(bake));
        return false;
    }
}
