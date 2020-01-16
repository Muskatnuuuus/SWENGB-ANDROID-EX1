package at.fh.swengb.loggingviewsandactivity


import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

object LessonApi {
    const val accessToken = "8f7e38ec-3630-4ac3-be9e-3189a0f74fd1"
    const val baseurl = "https://lessons.bloder.xyz"
    val retrofit: Retrofit
    val retrofitService: LessonApiService
    init {
        val moshi = Moshi.Builder().build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://lessons.bloder.xyz")
            .build()
        retrofitService = retrofit.create(LessonApiService::class.java)
    }
}
interface LessonApiService {
    @GET("/${LessonApi.accessToken}/lessons")
    fun lessons(): Call<List<Lesson>>
    @POST("/${LessonApi.accessToken}/lessons/{id}/rate")
    fun rateLesson(@Path("id") lessonId: String, @Body rating: LessonRating): Call<Unit>
    @GET("${LessonApi.baseurl}/${LessonApi.accessToken}/lessons/{id}")
    fun lessonById(@Query("id") lessonId: String): Call<Lesson>
}