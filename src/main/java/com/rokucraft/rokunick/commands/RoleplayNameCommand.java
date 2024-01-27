package com.rokucraft.rokunick.commands;

import com.rokucraft.rokunick.Permissions;
import com.rokucraft.rokunick.RokuNick;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.Command;
import org.incendo.cloud.bean.CommandBean;
import org.incendo.cloud.bean.CommandProperties;
import org.incendo.cloud.context.CommandContext;

import javax.inject.Inject;

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
        player.sendMessage(
                Component.text()
                        .append(Component.text("Your roleplay name is now", NamedTextColor.GREEN))
                        .append(Component.space())
                        .append(Component.text(name))
        );
    }
}
