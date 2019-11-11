package com.dc.storedataoffline.offlinecrud.retrofit

import com.dc.storedataoffline.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object RetrofitClient {

    //private const val BASE_URL = "http://192.168.0.6/myschool/scripts/"
    private const val BASE_URL = "https://api.myjson.com/bins/"
    private val okHttpClientBuilder = OkHttpClient.Builder()
        private val logInterceptor = HttpLoggingInterceptor()

    fun invoke(enableInterceptor: Boolean = true): Api {

        if(enableInterceptor && BuildConfig.DEBUG){
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        okHttpClientBuilder.addInterceptor(
                object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request = chain.request()
                        val newRequest =
                            request.newBuilder().
                                addHeader("Authorization", "secret_key")
                                .addHeader("Token", "secret_token")

                        return chain.proceed(newRequest.build())

                    }
                }).addInterceptor(logInterceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()


        return retrofit.create(Api::class.java)
    }
}