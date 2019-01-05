package space.dongik.artistpizza

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    val defaultText = "010-●●●●"
    var typedText = ""

    lateinit var vibe:Vibrator
    lateinit var vibrationEffect:VibrationEffect
    lateinit var apiService:ArtistpizzaApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrationEffect = VibrationEffect.createOneShot(30,100)
        apiService = ArtistpizzaApiService.create()
        phoneTextView.text = defaultText
        phoneTextView.gravity = Gravity.CENTER
        attendButton.visibility = View.INVISIBLE
        setNumpad()

        attendButton.setOnClickListener {
            val memberId = typedText
            Log.d(TAG, "attend requested")
            apiService.attend(AttendRequest(memberId))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result->
                    messageTextView.text = result.message
                    typedText = ""
                    phoneTextView.text = defaultText
                    Log.d(TAG, "message:${result.message}")
                }, { error ->
                    error.printStackTrace()
                    Log.d(TAG, error.toString())
                })
            phoneTextView.text = defaultText
        }
    }


    private fun setNumpad(){
        one.setOnClickListener { typeNumber(1) }
        two.setOnClickListener { typeNumber(2) }
        three.setOnClickListener { typeNumber(3) }
        four.setOnClickListener { typeNumber(4) }
        five.setOnClickListener { typeNumber(5) }
        six.setOnClickListener { typeNumber(6) }
        seven.setOnClickListener { typeNumber(7) }
        eight.setOnClickListener { typeNumber(8) }
        nine.setOnClickListener { typeNumber(9) }
        zero.setOnClickListener { typeNumber(0) }
        asterisk.setOnClickListener {
            vibe.vibrate(vibrationEffect)
            if (0 < typedText.length) {
                typedText = typedText.substring(0, typedText.length - 1)
                phoneTextView.text = defaultText + "-" +typedText
                messageTextView.text = ""
                attendButton.visibility = View.INVISIBLE
                if (typedText.length == 0) {
                    phoneTextView.text = defaultText
                }
            }
        }
        sharp.setOnClickListener {
            vibe.vibrate(vibrationEffect)
            typedText = ""
            phoneTextView.text = defaultText
            attendButton.visibility = View.INVISIBLE
        }
    }


    private fun typeNumber(number:Int){
        vibe.vibrate(vibrationEffect)
        if (typedText.length < 4) {
            typedText = typedText + number.toString()
            phoneTextView.text = defaultText + "-" + typedText
            if (typedText.length == 4) {
                apiService.ask(typedText)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                            result->
                        messageTextView.text = result.message
                        if (result.is_attendable) {
                            attendButton.visibility = View.VISIBLE
                        }
                    },{ error->
                        Log.d(TAG, error.toString())
                    })
            }
        }
    }

}
