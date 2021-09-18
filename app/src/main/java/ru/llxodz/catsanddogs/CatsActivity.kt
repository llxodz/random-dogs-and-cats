package ru.llxodz.catsanddogs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_cats.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.llxodz.catsanddogs.api.ApiRequest
import ru.llxodz.catsanddogs.api.BASE_URL_CATS

class CatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cats)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        makeApiRequest()

        floatingActionButton.setOnClickListener {
            floatingActionButton.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()

            makeApiRequest()
            iv_random_cat.visibility = View.GONE
        }
    }

    private fun makeApiRequest() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL_CATS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getRandomCat()

                withContext(Dispatchers.Main) {
                    Glide.with(applicationContext).load(response.file).into(iv_random_cat)
                    iv_random_cat.visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                makeApiRequest()
            }
        }
    }
}
