package com.example.pruebatecnica

import android.app.Application
import android.content.Context
import com.example.pruebatecnica.data.DataModuleProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@ExperimentalCoroutinesApi
@KoinApiExtension
class AppApplication : Application(){

    private var mContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        mContext = this
        initKoin()
    }

    fun getContext(): Context? {
        return mContext
    }

    @ExperimentalCoroutinesApi
    @KoinApiExtension
    private fun initKoin(){
        startKoin {
            androidContext(this@AppApplication)
            modules(DataModuleProvider.module)
        }
    }
}