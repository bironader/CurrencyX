package com.bironader.currencyx

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.bironader.currencyx.datasource.worker.DataWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltAndroidApp
class CurrencyXApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        applicationScope.launch {

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val work = PeriodicWorkRequestBuilder<DataWorker>(1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()


            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                DataWorker.WORKER_NAME,
                ExistingPeriodicWorkPolicy.REPLACE,
                work
            )
        }
    }

    override fun getWorkManagerConfiguration(): Configuration = Configuration.Builder()
        .setWorkerFactory(workerFactory)
        .build()
}
