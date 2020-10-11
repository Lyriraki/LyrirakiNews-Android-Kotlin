package com.lyriraki.lyrirakinews

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var topAnim : Animation
    private lateinit var botAnim : Animation
    private lateinit var logo : ImageView
    private lateinit var judul : TextView
    private lateinit var des : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        botAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        logo = findViewById(R.id.logo)
        judul = findViewById(R.id.judul)
        des = findViewById(R.id.des)

        logo.animation = topAnim
        judul.animation = botAnim
        des.animation = botAnim

        // Memindah setelah 5 detik di atas
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, Dashboard::class.java)
            startActivity(intent)
            finish() // Ditambahkan finish karena jika di back akan langsung keluar
        }, MainActivity.SPLASH_SCREEN.toLong())
    }

    companion object {
        private const val SPLASH_SCREEN = 4000
    }
}
