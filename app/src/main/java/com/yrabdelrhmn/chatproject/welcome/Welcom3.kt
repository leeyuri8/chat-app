package com.yrabdelrhmn.chatproject.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yrabdelrhmn.chatproject.R
import com.yrabdelrhmn.chatproject.auth.Signup
import kotlinx.android.synthetic.main.activity_welcom2.*

class Welcom3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom3)

        btnSkip.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
            finish()
        }

//        Handler().postDelayed({
//
//            val i = Intent(this, Signup::class.java)
//            startActivity(i)
//        }, 5000)
    }
}
