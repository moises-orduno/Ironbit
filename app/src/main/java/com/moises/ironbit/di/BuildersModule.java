package com.moises.ironbit.di;

import com.moises.ironbit.lobby.LobbyModule;
import com.moises.ironbit.lobby.LocalVenuesFragment;
import com.moises.ironbit.lobby.MainActivity;
import com.moises.ironbit.lobby.VenueFragment;
import com.moises.ironbit.lobby.VenuesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class BuildersModule {


    @ContributesAndroidInjector(modules = LobbyModule.class)
    abstract VenuesFragment bindVenuesFragment();

    @ContributesAndroidInjector(modules = LobbyModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = LobbyModule.class)
    abstract LocalVenuesFragment bindLocalVenuesFragment();

    @ContributesAndroidInjector(modules = LobbyModule.class)
    abstract VenueFragment bindVenueFragment();

    // Add bindings for other sub-components here
}
