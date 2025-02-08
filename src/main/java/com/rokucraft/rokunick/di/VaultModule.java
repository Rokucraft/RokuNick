package com.rokucraft.rokunick.di;

import com.rokucraft.rokunick.RokuNickPlugin;
import dagger.Module;
import dagger.Provides;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.plugin.RegisteredServiceProvider;

@Module
public class VaultModule {
    @Provides
    static Chat provideChat(RokuNickPlugin plugin) {
        RegisteredServiceProvider<Chat> rsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp == null) {
            throw new RuntimeException("Unable to find Vault Chat provider");
        }
        return rsp.getProvider();
    }
}
