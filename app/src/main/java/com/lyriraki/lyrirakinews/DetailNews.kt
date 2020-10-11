package com.lyriraki.lyrirakinews

import android.annotation.SuppressLint
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log

class DetailNews : AppCompatActivity() {
    lateinit var detailJudulNews: TextView
    private lateinit var detailAuthorNews: TextView
    private lateinit var detailDesNews: TextView
    private lateinit var detailPhotoNews: ImageView
    private lateinit var authorPhotoNews: ImageView


    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)

        detailJudulNews = findViewById(R.id.judul_news_detail)
        detailAuthorNews = findViewById(R.id.author_news_detail)
        detailDesNews = findViewById(R.id.des_news_detail)
        detailPhotoNews = findViewById(R.id.foto_news_detail)
        authorPhotoNews = findViewById(R.id.authorphoto_news_detail)
        detailDesNews.justificationMode = JUSTIFICATION_MODE_INTER_WORD

        if(intent != null) {
            detailJudulNews.text = intent.getStringExtra(EXTRA_judul)
            detailAuthorNews.text = intent.getStringExtra(EXTRA_author)
            detailDesNews.text = intent.getStringExtra(EXTRA_des)
            val pic = intent.getIntExtra(EXTRA_photo, 0)
            detailPhotoNews.setImageResource(pic)
            val picAuthor = intent.getIntExtra(EXTRA_authorphoto, 0)
            authorPhotoNews.setImageResource(picAuthor)

        }else{
            Toast.makeText(
                applicationContext,
                "No Data",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    companion object {
        var EXTRA_judul = "extra_judul"
        var EXTRA_author = "extra_author"
        var EXTRA_des = "extra_des"
        var EXTRA_photo = "extra_photo"
        var EXTRA_authorphoto = "extra_authorphoto"
    }
}
