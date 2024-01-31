package com.rokucraft.rokunick.data;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.OfflinePlayer;

import javax.inject.Inject;

public class VaultNameRepository implements NameRepository {

    private final Chat chat;

    @Inject
    public VaultNameRepository(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void setName(OfflinePlayer player, String key, String name) {
        chat.setPlayerInfoString(null, player, "name_" + key, name);
    }

    @Override
    public String getName(OfflinePlayer player, String key) {
        return chat.getPlayerInfoString(null, player, "name_" + key, null);
    }
}
