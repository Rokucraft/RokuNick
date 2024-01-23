package com.rokucraft.rokunick;

import cloud.commandframework.CommandManager;
import com.rokucraft.rokunick.di.DaggerRokuNickComponent;
import com.rokucraft.rokunick.di.RokuNickComponent;
import com.rokucraft.rokunick.papi.RokuNickPapiExpansion;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class RokuNickPlugin extends JavaPlugin {
    private RokuNick rokuNick;

    @Override
    public void onEnable() {
        super.onEnable();
        RokuNickComponent component = DaggerRokuNickComponent.builder()
                .plugin(this)
                .build();
        this.rokuNick = component.rokuNick();

        CommandManager<CommandSender> manager = component.commandManager();
        component.commands().forEach(manager::command);

        registerPapi();
    }

    private void registerPapi() {
        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new RokuNickPapiExpansion(rokuNick, this).register();
        }
    }
}
