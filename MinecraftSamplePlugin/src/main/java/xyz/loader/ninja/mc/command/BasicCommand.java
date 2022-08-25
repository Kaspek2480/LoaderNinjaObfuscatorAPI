package xyz.loader.ninja.mc.command;

import com.loader.ninja.NativeBridge;
import com.loader.ninja.spy.CallMethodSpy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

public class BasicCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length <= 0) {
            commandSender.sendMessage("Usage: /basic <decrypt/caller/get_secure_string> <arg>");
            return false;
        }

        switch (strings[0].toLowerCase(Locale.ROOT)) {
            case "decrypt": {
                commandSender.sendMessage(new String(NativeBridge.bake(strings[1].getBytes(StandardCharsets.UTF_8), 5), StandardCharsets.UTF_8));
                break;
            }
            case "caller": {
                commandSender.sendMessage(CallMethodSpy.INSTANCE.getSpy().fetchCallerMethod());
                break;
            }
            case "get_secure_string": {
                NativeBridge.getSecret(strings[1]).safeRevealString().ifPresent(commandSender::sendMessage);
                //commandSender.sendMessage(NativeBridge.getSecret(strings[1]).tryRevealString());
                break;
            }
            default:
                commandSender.sendMessage("Usage: /basic <decrypt/caller/get_secure_string> <arg>");
        }

        return true;
    }
}
