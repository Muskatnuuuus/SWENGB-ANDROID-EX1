package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_lesson_list.*


class LessonListActivity : AppCompatActivity() {
    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val ADD_OR_EDIT_RATING_REQUEST = 1
    }
    val lessonAdapter = LessonAdapter() { lesson ->
        val implicitIntent = Intent(this, LessonRatingActivity::class.java)
        implicitIntent.putExtra(EXTRA_LESSON_ID, lesson.id)
        startActivityForResult(implicitIntent, ADD_OR_EDIT_RATING_REQUEST)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        LessonRepository.lessonsList(
            success = {
                lessonAdapter.updateList(it)
            },
            error = {
                Toast.makeText(this, "Error. No list found!", Toast.LENGTH_SHORT)
            }
        )

        //lessonAdapter.updateList(LessonRepository.lessonsList())
        lesson_recycler_view.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        lesson_recycler_view.adapter = lessonAdapter
        parseJson()
        //Thread.sleep(5000)
        SleepyAsyncTask().execute()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ( resultCode == Activity.RESULT_OK && requestCode == ADD_OR_EDIT_RATING_REQUEST) {
            LessonRepository.lessonsList(
                success = {
                    lessonAdapter.updateList(it)
                },
                error = {
                    Toast.makeText(this, "Error. No list found!", Toast.LENGTH_SHORT)
                }
            )
        }
    }

    fun parseJson() {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<Lesson>(Lesson::class.java)

        val json = """
            {
                "id": "1",
                "name": "Lecture 0",
                "date": "09.10.2019",
                "topic": "Introduction",
                "type": "LECTURE",
                "lecturers": [
                    {
                        "name": "Lukas Bloder"
                    },
                    {
                        "name": "Sanja Illes"
                    }
                ],
                "ratings": []
            }
        """

        val result =jsonAdapter.fromJson(json)
        Log.e("MOSHI", result!!.name)

    }

}