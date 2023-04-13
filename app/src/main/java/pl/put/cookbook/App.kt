package pl.put.cookbook

import android.app.Application
import android.content.res.Resources

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appResources = resources
    }

    companion object {
        lateinit var appResources: Resources
            private set

        var timers: MutableList<Timer> = ArrayList()
    }
}