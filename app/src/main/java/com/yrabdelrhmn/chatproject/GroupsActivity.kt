package com.yrabdelrhmn.chatproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_groups.*

class GroupsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)

        start.setOnClickListener {
            val intent = Intent(this, ChatRoom::class.java)
            intent.putExtra("roomName", group_name.text.toString())
            startActivity(intent)
        }
    }
}
