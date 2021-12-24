package com.yrabdelrhmn.chatproject.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.yrabdelrhmn.chatproject.MainPage
import com.yrabdelrhmn.chatproject.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.textView3

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    //  private val id = UUID.randomUUID().toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        lateinit var topAnimation: Animation
        lateinit var bottomAnimation: Animation
        lateinit var middleAnimation: Animation

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Animation
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation)
        // bottom animation
        view.animation = bottomAnimation
        et_email_login.animation = bottomAnimation
        et_password_login.animation = bottomAnimation
        btn_login.animation = bottomAnimation
        img_social.animation = bottomAnimation
        textView3.animation = bottomAnimation
        tv_sign_up_login_screen.animation = bottomAnimation

        btn_login.setOnClickListener {
            val email = et_email_login.text.toString()
            val pass = et_password_login.text.toString()
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainPage::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { e ->
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun goToRegister(view: View) {
        val intent = Intent(this, Signup::class.java)
        startActivity(intent)
    }
}
