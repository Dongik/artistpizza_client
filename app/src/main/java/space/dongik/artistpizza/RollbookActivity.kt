package space.dongik.artistpizza

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.TableRow
import android.widget.TextView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_rollbook.*

class RollbookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rollbook)
        val apiService = ArtistpizzaApiService.create()
//        make it check per date
//
//        apiService.get_rollbook("190103")
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io()).subscribe({
//                rollbook->
//                val tableRow = TableRow(this)
//                val nameView = TextView(this)
//
////                tableLayout.addView(tableRow)
////                tableLayout
//                Log.d("Rollbook", rollbook.toString())
//            },{
//                error->
//                Log.d("Rollbook", error.toString())
//            })
    }

}
