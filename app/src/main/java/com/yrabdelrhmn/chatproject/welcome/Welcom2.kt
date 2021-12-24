package com.yrabdelrhmn.chatproject.welcome

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.yrabdelrhmn.chatproject.R
import kotlinx.android.synthetic.main.activity_welcom2.*

class Welcom2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom2)

        btnSkip.setOnClickListener {
            startActivity(Intent(this, Welcom3::class.java))
            finish()
        }
//        Handler().postDelayed({
//
//            val i = Intent(this, Welcom1::class.java)
//            startActivity(i)
//        }, 5000)

//        val background=object :Thread(){
//            override fun run() {
//                try {
//                    Thread.sleep(4000)
//                    val intt= Intent(baseContext,
//                        Welcom3::class.java)
//                    startActivity(intt)
//                    finish()
//                }catch (e:Exception){
//                    e.printStackTrace()
//                }
//            }
//
//        }
//        background.start()
    }
}
