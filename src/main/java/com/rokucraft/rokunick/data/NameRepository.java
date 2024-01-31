package com.rokucraft.rokunick.data;

import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;

public interface NameRepository {
    void setName(OfflinePlayer player, String key, Component name);
    Component getName(OfflinePlayer player, String key);
}
