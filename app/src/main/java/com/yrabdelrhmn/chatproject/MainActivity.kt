package com.yrabdelrhmn.chatproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.github.nkzawa.socketio.client.Socket
import com.yrabdelrhmn.chatproject.welcome.Welcom1
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var topAnimation: Animation
    lateinit var topAnimation1: Animation
    lateinit var mSocket: Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.scal)
        topAnimation1 = AnimationUtils.loadAnimation(this, R.anim.fead)

        logo.animation = topAnimation
        //   app_name.animation = topAnimation1

        spl.animate().translationY(-2500f).setDuration(1000).setStartDelay(2000)
        logo.animate().translationY(1800f).setDuration(1000).setStartDelay(1000)
        //   app_name.animate().translationY(1800f).setDuration(1000).setStartDelay(4000)

        Handler().postDelayed({

            val i = Intent(this, Welcom1::class.java)
            startActivity(i)
        }, 5000)
    }
}
