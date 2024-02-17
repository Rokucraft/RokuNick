package com.rokucraft.rokunick;

import com.rokucraft.rokunick.data.NameRepository;
import com.rokucraft.rokunick.event.NameChangeEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import javax.inject.Inject;

@NullMarked
public class NameManager {
    private final NameRepository nameRepository;

    @Inject
    public NameManager(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    public void setName(OfflinePlayer player, String key, @Nullable Component newName) {
        Component oldName = nameRepository.getName(player, key);
        NameChangeEvent event = new NameChangeEvent(player, key, oldName, newName);
        event.callEvent();
        if (event.isCancelled()) return;
        nameRepository.setName(event.getPlayer(), event.getKey(), event.getNewName());
    }

    @Nullable
    public Component getName(OfflinePlayer player, String key) {
        return nameRepository.getName(player, key);
    }
}
