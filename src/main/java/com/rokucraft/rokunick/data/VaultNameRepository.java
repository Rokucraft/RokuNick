package com.rokucraft.rokunick.data;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.OfflinePlayer;

import javax.inject.Inject;

public class VaultNameRepository implements NameRepository {

    private final Chat chat;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Inject
    public VaultNameRepository(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void setName(OfflinePlayer player, String key, Component name) {
        chat.setPlayerInfoString(null, player, "name_" + key, miniMessage.serialize(name));
    }

    @Override
    public Component getName(OfflinePlayer player, String key) {
        String serialized = chat.getPlayerInfoString(null, player, "name_" + key, null);
        if (serialized == null) return null;
        return miniMessage.deserialize(serialized);
    }
}
