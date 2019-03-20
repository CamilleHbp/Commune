package com.camillebc.commune.di.modules

import android.content.Context
import com.camillebc.fusy.utilities.HardwareStatusManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HardwareStatusModule {
    @Provides @Singleton
    fun providesHardwareStatusManager(context: Context) = HardwareStatusManager(context)
}