package at.fh.swengb.loggingviewsandactivity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LessonRating (val ratingValue: Double, val feedback: String) {
}