package pl.kylos.marczuk.pm_fm_test.repository.net

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface DataApi {
    @GET("recruitment-task")
    fun getList(): Observable<List<DataNet>>
}