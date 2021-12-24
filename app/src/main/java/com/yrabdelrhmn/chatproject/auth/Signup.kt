package com.yrabdelrhmn.chatproject.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.nkzawa.socketio.client.Socket
import com.google.firebase.auth.FirebaseAuth
import com.yrabdelrhmn.chatproject.MainPage
import com.yrabdelrhmn.chatproject.R
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    lateinit var topAnimation: Animation
    lateinit var bottomAnimation: Animation
    lateinit var middleAnimation: Animation
    private val id = UUID.randomUUID().toString()
    private var mSocket: Socket? = null
    var imageURI = ""
    var lngUser = ""
    var latUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        // Animation

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation)

        // bottom animation
        view_sign_up.animation = bottomAnimation
        et_username_signup.animation = bottomAnimation
        et_email_sign_up.animation = bottomAnimation
        et_password_sign_up.animation = bottomAnimation
        btn_sign_up.animation = bottomAnimation
        view2_sign_up.animation = bottomAnimation
        tv_login.animation = bottomAnimation
        textView3.animation = bottomAnimation
        // middle animation
        tv_Sign_up_signup_screen.animation = middleAnimation

//         top animation
//        imageView_girl.animation = topAnimation
//        textView_home.animation = topAnimation
//        tv_shopping.animation = topAnimation

        // call function and save& ensure the data user and go to login screen
        btn_sign_up.setOnClickListener {
            val email = et_email_sign_up.text.toString()
            val pass = et_password_sign_up.text.toString()
            val userName = et_username_signup.text.toString()

            // Signup with Firebase Authentecation 
            auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainPage::class.java)
                        intent.putExtra("id", id)
                        intent.putExtra("username", userName)
                        startActivity(intent)
                        finish()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_LONG).show()
                }
        }

        // go to Login Screen if you can account
//        tv_login.setOnClickListener {
//            startActivity(Intent(this, Login::class.java))
//            finish()
//        }
    }

    fun gotoLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}
