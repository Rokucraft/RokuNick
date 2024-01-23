package com.rokucraft.rokunick.di;

import com.rokucraft.rokunick.RokuNick;
import com.rokucraft.rokunick.RokuNickPlugin;
import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {CommandsModule.class, VaultModule.class})
public interface RokuNickComponent {
    RokuNick rokuNick();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder plugin(RokuNickPlugin plugin);
        RokuNickComponent build();
    }
}
