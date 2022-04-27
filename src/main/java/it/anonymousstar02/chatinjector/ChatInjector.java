package it.anonymousstar02.chatinjector;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Matcher;

public final class ChatInjector extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
    }

    @EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
    public void onChat(AsyncPlayerChatEvent event)
    {
        Player p = event.getPlayer();

        String message = event.getMessage();

        Matcher matcher = PlaceholderAPI.getBracketPlaceholderPattern().matcher(message);

        if (matcher.find()) {
            message = PlaceholderAPI.setBracketPlaceholders(p, message);
        }

        event.setMessage(message);

        String format = event.getFormat();

        matcher = PlaceholderAPI.getBracketPlaceholderPattern().matcher(format);

        if (matcher.find()) {
            format = PlaceholderAPI.setBracketPlaceholders(p, format);
            event.setFormat(format);
        }
    }

}
