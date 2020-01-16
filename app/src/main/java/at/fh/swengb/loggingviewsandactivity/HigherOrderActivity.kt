package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class HigherOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_order)

        //myHigherOrderFunction{a -> a*102}
        myHigherOrderFunction(fun(a: Int): Int = a * 102)

        kotlinHigherOrderWithList()
    }
    private fun myHigherOrderFunction(param: (Int) -> Int) {
        if (param(6) == 612) {
            Log.e("HIGHER_ORDER", "Congrats, your first lambda works :)")
        }
    }

    private fun kotlinHigherOrderWithList() {
        /*val list = LessonRepository.lessonsList()


        val heldByBloder = list.filter { Lecturer("Lukas Bloder") in it.lecturers }.map { x -> "${x.name} - ${x.topic}"}.toString()
        Log.e("HELD BY BLODER", heldByBloder)

        val topicMap = list.groupBy { it.topic }.toString()
        Log.e("TOPIC MAP", topicMap)

        val avgLecture = list.map {it.ratings.map { it.ratingValue }}.flatten().average().toString()
        Log.e("AVERAGE LECTURES", avgLecture)*/

    }
}
