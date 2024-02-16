package com.rokucraft.rokunick.data;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.OfflinePlayer;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import javax.inject.Inject;

@NullMarked
public class VaultNameRepository implements NameRepository {

    private final Chat chat;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Inject
    public VaultNameRepository(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void setName(OfflinePlayer player, String key, @Nullable Component name) {
        chat.setPlayerInfoString(null, player, "name_" + key, name != null ? miniMessage.serialize(name) : null);
    }

    @Override
    @Nullable
    public Component getName(OfflinePlayer player, String key) {
        String serialized = chat.getPlayerInfoString(null, player, "name_" + key, null);
        return (serialized != null) ? miniMessage.deserialize(serialized) : null;
    }
}
