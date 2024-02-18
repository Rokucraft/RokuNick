package com.rokucraft.rokunick.commands;

import com.rokucraft.rokunick.NameKeys;
import com.rokucraft.rokunick.NameManager;
import com.rokucraft.rokunick.Permissions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.Command;
import org.incendo.cloud.bean.CommandBean;
import org.incendo.cloud.bean.CommandProperties;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.processors.cooldown.Cooldown;
import org.incendo.cloud.processors.cooldown.DurationFunction;
import org.jspecify.annotations.NullMarked;

import javax.inject.Inject;
import java.time.Duration;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;
import static net.kyori.adventure.text.format.NamedTextColor.RED;
import static org.incendo.cloud.description.CommandDescription.commandDescription;

@NullMarked
public class RemoveRoleplayNameCommand extends CommandBean<CommandSender> {

    private final NameManager nameManager;

    @Inject
    public RemoveRoleplayNameCommand(
            NameManager nameManager
    ) {
        this.nameManager = nameManager;
    }

    @Override
    protected CommandProperties properties() {
        return CommandProperties.of("rpname");
    }

    @Override
    protected Command.Builder<? extends CommandSender> configure(Command.Builder<CommandSender> builder) {
        return builder
                .literal("off")
                .commandDescription(commandDescription("Remove your roleplay name"))
                .permission(Permissions.COMMAND_REMOVENAME_ROLEPLAY)
                .senderType(Player.class)
                .handler(this::handle)
                .apply(Cooldown.of(DurationFunction.constant(Duration.ofSeconds(30))));
    }

    public void handle(CommandContext<Player> ctx) {
        final Player player = ctx.sender();
        if (nameManager.getName(player, NameKeys.ROLEPLAY) == null) {
            player.sendMessage(text("You do not have a roleplay name set", RED));
            return;
        }
        nameManager.setName(player, NameKeys.ROLEPLAY, null);
        player.sendMessage(text("Your roleplay name has been removed", GREEN));
    }
}
