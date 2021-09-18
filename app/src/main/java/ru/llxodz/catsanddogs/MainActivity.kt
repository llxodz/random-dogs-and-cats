package ru.llxodz.catsanddogs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_cats_picture.setOnClickListener {
            val intent = Intent(this, CatsActivity::class.java)
            startActivity(intent)
        }

        btn_dogs_picture.setOnClickListener {
            val intent = Intent(this, DogsActivity::class.java)
            startActivity(intent)
        }
    }
}
