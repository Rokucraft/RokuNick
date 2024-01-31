package com.rokucraft.rokunick.papi;

import com.rokucraft.rokunick.NameManager;
import com.rokucraft.rokunick.Permissions;
import com.rokucraft.rokunick.RokuNickPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

public class RokuNickPapiExpansion extends PlaceholderExpansion {

    private final NameManager nameManager;
    private final RokuNickPlugin plugin;

    private final LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.legacySection();

    @Inject
    public RokuNickPapiExpansion(NameManager nameManager, RokuNickPlugin plugin) {
        this.nameManager = nameManager;
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
    public @Nullable String onRequest(OfflinePlayer offlinePlayer, @NotNull String params) {
        Player player = offlinePlayer.getPlayer();
        // Don't parse for offline players
        if (player == null) {
            return null;
        }

        if (params.equalsIgnoreCase("name_roleplay")) {
            return getRoleplayNameOrFallback(player);
        }
        return null;
    }

    private String getRoleplayNameOrFallback(Player player) {
        Component roleplayName = nameManager.getRoleplayName(player);
        if (player.hasPermission(Permissions.NAME_ROLEPLAY) && roleplayName != null) {
            return legacySerializer.serialize(roleplayName);
        }
        return legacySerializer.serialize(player.displayName());
    }
}
