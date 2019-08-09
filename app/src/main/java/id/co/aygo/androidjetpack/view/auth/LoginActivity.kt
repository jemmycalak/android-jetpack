package id.co.aygo.androidjetpack.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import id.co.aygo.androidjetpack.R
import id.co.aygo.androidjetpack.view.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onLogin(v:View){
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
