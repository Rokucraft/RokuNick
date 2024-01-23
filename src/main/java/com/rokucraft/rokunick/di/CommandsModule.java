package com.rokucraft.rokunick.di;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import cloud.commandframework.execution.ExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import com.rokucraft.rokunick.RokuNick;
import com.rokucraft.rokunick.RokuNickPlugin;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static cloud.commandframework.arguments.standard.StringParser.stringParser;

@Module
public class CommandsModule {
    @Provides
    public CommandManager<CommandSender> provideCommandManager(RokuNickPlugin plugin) {
        var manager = PaperCommandManager.createNative(plugin, ExecutionCoordinator.simpleCoordinator());
        manager.registerBrigadier();
        return manager;
    }

    @Provides
    @IntoSet
    public Command<? extends CommandSender> roleplayNameCommand(
            CommandManager<CommandSender> manager,
            RokuNick rokuNick
    ) {
        return manager.commandBuilder("rpname")
                .required("name", stringParser())
                .senderType(Player.class)
                .handler(ctx -> {
                    final Player player = ctx.sender();
                    final String name = ctx.get("name");
                    rokuNick.setRoleplayName(player, name);
                }).build();
    }
}
