package space.dongik.artistpizza

import io.reactivex.Observable
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.*


interface ArtistpizzaApiService {

    @GET("members")
    fun get_members(): Observable<Array<Member>>

    @GET("members")
    fun get_member(@Query("member_id")memberId:String): Observable<Member>

    @GET("ask/{pin}")
    fun ask(@Path("pin")memberId: String): Observable<AskResult>

    @POST("members")
    fun add_member(@Body member:Member):Observable<Member>

    @PUT("members")
    fun edit_member(@Body member:Member):Observable<Member>


//    @GET("rollbook/{time}")
//    fun get_rollbook(@Path("time")time: String):Observable<Rollbook>
//
//    @POST("rollbook/{time}")
//    fun add_rollbook(@Path("time")time: String, @Body rollbook:Rollbook): Observable<Rollbook>
//
//    @PUT("rollbook/{time}")
//    fun edit_rollbook(@Path("time")time: String, @Body rollbook:Rollbook):Observable<Rollbook>


    @POST("attend")
    fun attend(@Body attendRequest:AttendRequest):Observable<AttendRequestResult>
    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): ArtistpizzaApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.0.30:5000/")
                .build()

            return retrofit.create(ArtistpizzaApiService::class.java);
        }
    }
}
