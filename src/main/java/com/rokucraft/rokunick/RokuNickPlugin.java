package com.rokucraft.rokunick;

import com.rokucraft.rokunick.di.DaggerRokuNickComponent;
import com.rokucraft.rokunick.papi.RokuNickPapiExpansion;
import org.bukkit.plugin.java.JavaPlugin;

public class RokuNickPlugin extends JavaPlugin {
    private RokuNick rokuNick;

    @Override
    public void onEnable() {
        super.onEnable();
        this.rokuNick = DaggerRokuNickComponent.builder()
                .plugin(this)
                .build()
                .rokuNick();

        registerPapi();
    }

    private void registerPapi() {
        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new RokuNickPapiExpansion(rokuNick, this).register();
        }
    }
}
