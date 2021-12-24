package com.yrabdelrhmn.chatproject.welcome

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.yrabdelrhmn.chatproject.R
import kotlinx.android.synthetic.main.activity_welcom2.*

class Welcom1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom1)

        btnSkip.setOnClickListener {
            startActivity(Intent(this, Welcom2::class.java))
            finish()
        }
//        Handler().postDelayed({
//            val i = Intent(this, Welcom2::class.java)
//            startActivity(i)
//        }, 5000)
    }
}
