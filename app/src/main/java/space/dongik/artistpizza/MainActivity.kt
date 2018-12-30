package space.dongik.artistpizza

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

//    var todayDate = Calendar.getInstance().getTime()
//    var formatter = SimpleDateFormat("yymmdd")
//    val date = formatter.format(todayDate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = ArtistpizzaApiService.create()
        val rollbookDate = 190103
        textView.text = "출석부 " + rollbookDate.toString()
        editNumber.gravity = Gravity.CENTER
        attendButton.setOnClickListener {
            val memberId = editNumber.text.toString()
            Log.d(TAG, "attend requested")
            apiService.attend(AttendRequest(memberId, rollbookDate))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result->
                    Log.d(TAG, "message:${result.message}")
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }, { error ->
                    error.printStackTrace()
                    Log.d(TAG, error.toString())
                })
        }
    }
}
