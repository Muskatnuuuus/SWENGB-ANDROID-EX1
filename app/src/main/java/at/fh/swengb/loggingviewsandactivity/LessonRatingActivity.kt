package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import at.fh.swengb.loggingviewsandactivity.LessonListActivity.Companion.EXTRA_LESSON_ID
import kotlinx.android.synthetic.main.activity_lesson_rating.*

class LessonRatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)

        if (lessonId == null) {
            Toast.makeText(this, "No lessonID given", Toast.LENGTH_SHORT).show()
            finish()
        } else {

            LessonRepository.lessonById(lessonId,
                success = {
                    lesson_rating_header.text = it.name
                },
                error = {
                Log.e("Error", "No valid ID")
            }
            )
            //val lessonName = LessonRepository.lessonById(lessonId)?.name
            //lesson_rating_header.text = lessonName

            rate_lesson.setOnClickListener{
                val ratingValue = lesson_rating_bar.rating.toDouble()
                val feedback = lesson_feedback.text.toString()
                val rating = LessonRating(ratingValue, feedback)

                LessonRepository.rateLesson(lessonId,rating,
                    success = {
                        setResult(Activity.RESULT_OK)
                        finish()
                },
                    error = {
                        Log.e("Error", "No valid ID")
                    }
                )


            }
            lesson_rating_bar.rating.toDouble()
            lesson_feedback.text.toString()
        }
    }
}
