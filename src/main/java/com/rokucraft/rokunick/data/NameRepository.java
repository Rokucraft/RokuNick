package com.rokucraft.rokunick.data;

import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public interface NameRepository {
    void setName(OfflinePlayer player, String key, @Nullable Component name);

    @Nullable Component getName(OfflinePlayer player, String key);
}
