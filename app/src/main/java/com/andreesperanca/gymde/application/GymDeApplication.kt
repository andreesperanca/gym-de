package com.andreesperanca.gymde.application

import android.app.Application
import com.andreesperanca.gymde.di.modules.firebaseModules
import com.andreesperanca.gymde.di.modules.repositoriesModules
import com.andreesperanca.gymde.di.modules.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GymDeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GymDeApplication)
            modules(firebaseModules)
            modules(repositoriesModules)
            modules(viewModelModules)
        }
    }
}