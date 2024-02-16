package com.rokucraft.rokunick;

import com.rokucraft.rokunick.di.DaggerRokuNickComponent;
import com.rokucraft.rokunick.di.RokuNickComponent;
import com.rokucraft.rokunick.discord.WebhookExecutor;
import com.rokucraft.rokunick.papi.RokuNickPapiExpansion;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.CommandManager;

public class RokuNickPlugin extends JavaPlugin {
    private NameManager nameManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        RokuNickComponent component = DaggerRokuNickComponent.builder()
                .plugin(this)
                .build();
        this.nameManager = component.nameManager();

        CommandManager<CommandSender> manager = component.commandManager();
        component.commands().forEach(manager::command);

        registerPapi();
        setupDiscordWebhooks();
    }

    private void registerPapi() {
        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new RokuNickPapiExpansion(nameManager, this).register();
        }
    }

    private void setupDiscordWebhooks() {
        String webhookUrl = getConfig().getString("webhook-url");
        if (webhookUrl == null) {
            getSLF4JLogger().info("Discord webhooks are disabled");
            return;
        }
        this.getServer().getPluginManager().registerEvents(new WebhookExecutor(webhookUrl), this);
        getSLF4JLogger().info("Enabled Discord webhooks");
    }
}
