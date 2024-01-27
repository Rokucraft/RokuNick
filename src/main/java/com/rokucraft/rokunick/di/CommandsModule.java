package com.rokucraft.rokunick.di;

import com.rokucraft.rokunick.RokuNickPlugin;
import com.rokucraft.rokunick.commands.RoleplayNameCommand;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.bean.CommandBean;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;
import org.incendo.cloud.processors.cooldown.CooldownConfiguration;
import org.incendo.cloud.processors.cooldown.CooldownManager;
import org.incendo.cloud.processors.cooldown.CooldownRepository;
import org.incendo.cloud.processors.cooldown.listener.CooldownActiveListener;
import org.incendo.cloud.processors.cooldown.listener.ScheduledCleanupCreationListener;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

@Module
public abstract class CommandsModule {
    @Provides
    static CommandManager<CommandSender> provideCommandManager(
            RokuNickPlugin plugin,
            CooldownManager<CommandSender> cooldownManager
    ) {
        var manager = PaperCommandManager.createNative(plugin, ExecutionCoordinator.simpleCoordinator());
        manager.registerBrigadier();
        manager.registerCommandPostProcessor(cooldownManager.createPostprocessor());
        return manager;
    }

    @Provides
    static CooldownManager<CommandSender> provideCooldownManager() {
        CooldownRepository<CommandSender> repository = CooldownRepository.forMap(new HashMap<>());
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        CooldownActiveListener<CommandSender> onActive = (sender, command, cooldown, remaining) ->
                sender.sendMessage(
                        text("Please wait " + remaining.getSeconds() + " seconds before trying again.", RED)
                );

        return CooldownManager.of(
                CooldownConfiguration.<CommandSender>builder()
                        .repository(repository)
                        .addCreationListener(new ScheduledCleanupCreationListener<>(executorService, repository))
                        .addActiveCooldownListener(onActive)
                        .build()
        );
    }

    @Binds
    @IntoSet
    abstract CommandBean<CommandSender> roleplayNameCommand(RoleplayNameCommand command);
}
