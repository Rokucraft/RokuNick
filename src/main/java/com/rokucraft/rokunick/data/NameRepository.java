package com.rokucraft.rokunick.data;

import org.bukkit.OfflinePlayer;

public interface NameRepository {
    void setName(OfflinePlayer player, String key, String name);
    String getName(OfflinePlayer player, String key);
}
