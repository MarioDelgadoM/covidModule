package cl.mario.covid.covidmodule.data.remote

import cl.mario.covid.covidmodule.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class CovidInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("X-RapidAPI-Key", BuildConfig.RapidApiKey)
            .build()
        return chain.proceed(request)
    }
}