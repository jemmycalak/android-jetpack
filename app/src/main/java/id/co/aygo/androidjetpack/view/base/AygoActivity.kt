package id.co.aygo.androidjetpack.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class AygoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(OnView())
    }

    protected abstract fun OnView(): Int
}