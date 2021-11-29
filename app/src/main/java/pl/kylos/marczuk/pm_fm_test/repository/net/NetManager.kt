package pl.kylos.marczuk.pm_fm_test.repository.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

const val API_URL = "https://recruitment-task.futuremind.dev"

class NetManager {
    private val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val api: DataApi = retrofit.create(DataApi::class.java)
}