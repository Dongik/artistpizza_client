package space.dongik.artistpizza

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import space.dongik.artistpizza.R.layout.activity_members

class MembersActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_members)
        val apiService = ArtistpizzaApiService.create()
        val members = apiService.get_members()
        


    }
}