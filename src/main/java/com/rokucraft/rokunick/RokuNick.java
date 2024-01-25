package com.rokucraft.rokunick;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.inject.Inject;

public class RokuNick {
    public static final String RP_NAME_KEY = "rp_name";
    private final Chat chat;

    @Inject
    public RokuNick(
            Chat chat
    ) {
        this.chat = chat;
    }

    public void setRoleplayName(OfflinePlayer player, String name) {
        chat.setPlayerInfoString(null, player, RP_NAME_KEY, name);
    }

    public String getRoleplayName(OfflinePlayer player) {
        return chat.getPlayerInfoString(null, player, RP_NAME_KEY, null);
    }
}
