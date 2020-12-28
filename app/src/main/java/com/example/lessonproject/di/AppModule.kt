package com.example.lessonproject.di

import android.content.Context
import androidx.room.Room
import com.example.lessonproject.AppSetting
import com.example.lessonproject.feature.data.NoteDB
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context) = Room.databaseBuilder(
        context,
        NoteDB::class.java, "note"
    )
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return Retrofit.Builder()
            .baseUrl("http://k92444b8.beget.tech/")
            .client(OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("User-Token", AppSetting.userToken)
                        .addHeader(
                            "User-Agent",
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36"
                        )
                        .build()
                    chain.proceed(request)
                }
                .addInterceptor(interceptor).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteApi(retrofit: Retrofit): NoteApi = retrofit.create(NoteApi::class.java)
}