package com.rokucraft.rokunick.papi;

import com.rokucraft.rokunick.RokuNick;
import com.rokucraft.rokunick.RokuNickPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

public class RokuNickPapiExpansion extends PlaceholderExpansion {

    private final RokuNick rokuNick;
    private final RokuNickPlugin plugin;

    @Inject
    public RokuNickPapiExpansion(RokuNick rokuNick, RokuNickPlugin plugin) {
        this.rokuNick = rokuNick;
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "rokunick";
    }

    @Override
    @SuppressWarnings("experimentalApi")
    public @NotNull String getAuthor() {
        return String.join(",", plugin.getPluginMeta().getAuthors());
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getPluginMeta().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("name_roleplay")) {
            return rokuNick.getRoleplayName(player);
        }
        return null;
    }
}
