package at.fh.swengb.loggingviewsandactivity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Lesson (val id: String,
              val name: String,
              val date: String,
              val topic: String,
              val type: LessonType,
              val lecturers: List<Lecturer>,
              val ratings: MutableList<LessonRating>)
{

    fun ratingAverage(): Double {
        var sum = 0.0
        ratings.forEach {
            sum += it.ratingValue
        }

    val average = String.format("%.3f", sum / ratings.count()).toDouble()
    return average
    }

    override fun toString(): String {
        return "Lesson(id='$id', name='$name', date='$date', topic='$topic', type=$type, lecturers=$lecturers, ratings=$ratings)"
    }

}