package com.rokucraft.rokunick;

import com.rokucraft.rokunick.data.NameRepository;
import com.rokucraft.rokunick.event.NameChangeEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;

import javax.inject.Inject;

public class NameManager {
    public static final String RP_NAME_KEY = "roleplay";
    private final NameRepository nameRepository;

    @Inject
    public NameManager(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    public void setRoleplayName(OfflinePlayer player, Component newName) {
        Component oldName = nameRepository.getName(player, RP_NAME_KEY);
        NameChangeEvent event = new NameChangeEvent(player, RP_NAME_KEY, oldName, newName);
        if (event.isCancelled()) return;
        nameRepository.setName(event.getPlayer(), event.getKey(), event.getNewName());
    }

    public Component getRoleplayName(OfflinePlayer player) {
        return nameRepository.getName(player, RP_NAME_KEY);
    }
}
