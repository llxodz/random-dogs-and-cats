package ru.llxodz.catsanddogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_cats.*
import kotlinx.android.synthetic.main.activity_cats.floatingActionButton
import kotlinx.android.synthetic.main.activity_cats.iv_random_cat
import kotlinx.android.synthetic.main.activity_dogs.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.llxodz.catsanddogs.api.ApiRequest
import ru.llxodz.catsanddogs.api.BASE_URL_CATS
import ru.llxodz.catsanddogs.api.BASE_URL_DOGS

class DogsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        makeApiRequest()

        floatingActionButton.setOnClickListener {
            floatingActionButton.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()

            makeApiRequest()
            iv_random_dog.visibility = View.GONE
        }
    }

    private fun makeApiRequest() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL_DOGS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getRandomDog()

                if (response.fileSizeBytes < 400_000) {
                    withContext(Dispatchers.Main) {
                        Glide.with(applicationContext).load(response.url).into(iv_random_dog)
                        iv_random_dog.visibility = View.VISIBLE
                    }
                } else {
                    makeApiRequest()
                }
            } catch (e: Exception) {
                makeApiRequest()
            }
        }
    }
}
