package com.rokucraft.rokunick.discord;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedAuthor;
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedField;
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedTitle;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import com.rokucraft.rokunick.event.NameChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

import static net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText;

@NullMarked
public class WebhookExecutor implements Listener {

    private final String webhookUrl;

    public WebhookExecutor(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    @EventHandler
    public void onNameChange(NameChangeEvent e) {
        String name;
        if (e.getPlayer().getName() != null) {
            name = e.getPlayer().getName();
        } else {
            name = e.getPlayer().getUniqueId().toString();
        }

        sendEmbed(
                name,
                e.getPlayer().getUniqueId(),
                e.getKey(),
                e.getOldName() != null ? plainText().serialize(e.getOldName()) : null,
                e.getNewName() != null ? plainText().serialize(e.getNewName()) : null
        );

    }

    private void sendEmbed(
            String playerName,
            UUID uuid,
            String key,
            @Nullable String oldName,
            @Nullable String newName
    ) {
        WebhookEmbedBuilder embed = new WebhookEmbedBuilder()
                .setTitle(new EmbedTitle(
                        titleCase(key) + " name " + ((newName != null) ? "changed" : "removed"),
                        null
                ))
                .setColor(0x00d398)
                .setAuthor(
                        new EmbedAuthor(
                                playerName,
                                avatarUrl(uuid),
                                null
                        )
                );
        if (oldName != null) {
            embed.addField(new EmbedField(true, "From", oldName));
        }
        if (newName != null) {
            embed.addField(new EmbedField(true, "To", newName));
        }
        embed.addField(new EmbedField(false, "UUID", "`%s`".formatted(uuid)));

        try (var client = WebhookClient.withUrl(webhookUrl)) {
            client.send(embed.build());
        }
    }

    private String avatarUrl(UUID uuid) {
        return "https://crafatar.com/avatars/" + uuid + "?overlay";
    }

    private String titleCase(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
