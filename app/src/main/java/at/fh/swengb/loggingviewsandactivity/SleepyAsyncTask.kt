package at.fh.swengb.loggingviewsandactivity

import android.os.AsyncTask
import android.util.Log

class SleepyAsyncTask : AsyncTask<Unit, Unit, Unit>() {

    override fun onPreExecute() {
        super.onPreExecute()
        Log.e("SleepingAsyncTask", "Going to sleep")
        Log.e("Sleep1",Thread.currentThread().name)
    }

    override fun doInBackground(vararg params: Unit?) {
        Thread.sleep(5000)
        Log.e("Sleep2",Thread.currentThread().name)
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        Log.e("SleepingAsyncTask", "Done sleeping")
        Log.e("Sleep3",Thread.currentThread().name)
    }
}