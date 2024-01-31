package com.rokucraft.rokunick.di;

import com.rokucraft.rokunick.data.NameRepository;
import com.rokucraft.rokunick.data.VaultNameRepository;
import dagger.Binds;
import dagger.Module;

@Module
abstract class DataModule {
    @Binds
    abstract NameRepository bindNameRepository(VaultNameRepository repository);
}
