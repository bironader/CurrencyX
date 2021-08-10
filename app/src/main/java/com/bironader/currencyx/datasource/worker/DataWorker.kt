package com.bironader.currencyx.datasource.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bironader.domain.usecases.abstraction.CurrencyUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class DataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val currencyUseCase: CurrencyUseCase
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        const val WORKER_NAME = "com.bironader.currencyx.datasource.worker.DataWorker"
    }

    override suspend fun doWork(): Result {

        try {
            currencyUseCase.syncData()
            Timber.d("work manager is sync")
        } catch (e: Exception) {
            Timber.d("sync failed$e")
            return Result.retry()
        }
        return Result.success()
    }

}