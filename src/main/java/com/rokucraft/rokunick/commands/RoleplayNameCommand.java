package com.rokucraft.rokunick.commands;

import com.rokucraft.rokunick.Permissions;
import com.rokucraft.rokunick.RokuNick;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.Command;
import org.incendo.cloud.bean.CommandBean;
import org.incendo.cloud.bean.CommandProperties;
import org.incendo.cloud.context.CommandContext;

import javax.inject.Inject;

import static net.kyori.adventure.text.Component.space;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;

public class RoleplayNameCommand extends CommandBean<CommandSender> {

    private final RokuNick rokuNick;

    @Inject
    public RoleplayNameCommand(
            RokuNick rokuNick
    ) {
        this.rokuNick = rokuNick;
    }

    @Override
    protected @NonNull CommandProperties properties() {
        return CommandProperties.of("rpname");
    }

    @Override
    protected Command.Builder<? extends CommandSender> configure(Command.Builder<CommandSender> builder) {
        return builder
                .permission(Permissions.COMMAND_SETNAME_ROLEPLAY)
                .senderType(Player.class)
                .handler(this::handle);
    }

    public void handle(@NonNull CommandContext<Player> ctx) {
        final Player player = ctx.sender();
        final String name = ctx.get("name");
        rokuNick.setRoleplayName(player, name);
        player.sendMessage(text()
                .append(text("Your roleplay name is now", GREEN))
                .append(space())
                .append(text(name))
        );
    }
}
