package com.rokucraft.rokunick;

import com.rokucraft.rokunick.event.NameChangeEvent;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.OfflinePlayer;

import javax.inject.Inject;

public class NameManager {
    public static final String RP_NAME_KEY = "rp_name";
    private final Chat chat;

    @Inject
    public NameManager(
            Chat chat
    ) {
        this.chat = chat;
    }

    public void setRoleplayName(OfflinePlayer player, String name) {
        NameChangeEvent event = new NameChangeEvent(player, "roleplay", name);
        if (event.isCancelled()) return;
        chat.setPlayerInfoString(null, event.getPlayer(), RP_NAME_KEY, event.getName());
    }

    public String getRoleplayName(OfflinePlayer player) {
        return chat.getPlayerInfoString(null, player, RP_NAME_KEY, null);
    }
}
