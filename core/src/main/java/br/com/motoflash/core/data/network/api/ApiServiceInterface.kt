package br.com.motoflash.core.data.network.api

import br.com.motoflash.core.BuildConfig
import com.google.gson.JsonObject
import io.ktor.util.Hash
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiServiceInterface {

    //Client Start

    //Profile Start
    @POST("users")
    fun doCreateUser(
        @Body body: JsonObject
    ) : Observable<HashMap<String, Any>>
    //Profile End

    //Client End

    //Courier Start

    //Profile Start
    @POST("couriers")
    fun doCreateCourier(
        @Body body: JsonObject
    ) : Observable<HashMap<String, Any>>
    //Profile End

    //Courier End

    companion object Factory {
        fun create(): ApiServiceInterface {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()

            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}