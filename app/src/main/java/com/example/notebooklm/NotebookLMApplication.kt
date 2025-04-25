package com.example.notebooklm

import android.app.Application
import com.example.notebooklm.data.api.NotebookLMService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NotebookLMApplication : Application() {
    lateinit var notebookLMService: NotebookLMService
        private set

    override fun onCreate() {
        super.onCreate()
        
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://notebooklm.googleapis.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        notebookLMService = retrofit.create(NotebookLMService::class.java)
    }
} 