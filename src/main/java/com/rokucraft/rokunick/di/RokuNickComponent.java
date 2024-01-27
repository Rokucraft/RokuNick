package com.rokucraft.rokunick.di;

import com.rokucraft.rokunick.RokuNick;
import com.rokucraft.rokunick.RokuNickPlugin;
import dagger.BindsInstance;
import dagger.Component;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.Command;
import org.incendo.cloud.CommandManager;

import java.util.Set;

@Component(modules = {CommandsModule.class, VaultModule.class})
public interface RokuNickComponent {
    RokuNick rokuNick();

    CommandManager<CommandSender> commandManager();
    Set<Command<? extends CommandSender>> commands();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder plugin(RokuNickPlugin plugin);
        RokuNickComponent build();
    }
}
