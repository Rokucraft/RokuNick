package com.rokucraft.rokunick.event;

import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class NameChangeEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final OfflinePlayer player;
    private final String key;
    private final Component oldName;
    private Component newName;
    private boolean cancelled;

    public NameChangeEvent(OfflinePlayer player, String key, Component oldName, Component newName) {
        this.player = player;
        this.key = key;
        this.oldName = oldName;
        this.newName = newName;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }


    public OfflinePlayer getPlayer() {
        return player;
    }

    public String getKey() {
        return key;
    }

    public Component getOldName() {
        return oldName;
    }

    public Component getNewName() {
        return newName;
    }

    public void setNewName(Component newName) {
        this.newName = newName;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
