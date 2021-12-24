package com.yrabdelrhmn.chatproject
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.github.nkzawa.socketio.client.Socket.EVENT_CONNECT_ERROR
import com.google.gson.Gson
import com.yrabdelrhmn.chatproject.adapter.UserAdapter
import com.yrabdelrhmn.chatproject.constant.Keys.ALLUSERS
import com.yrabdelrhmn.chatproject.models.User
import com.yrabdelrhmn.chatproject.models.initialData
import com.yrabdelrhmn.chatproject.server.SocketHandler
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.activity_main_page.view.*
import kotlinx.android.synthetic.main.user_item.*
import org.json.JSONException
import org.json.JSONObject
import kotlin.collections.ArrayList

class MainPage : AppCompatActivity() {
    lateinit var mSocket: Socket
    val gson: Gson = Gson()
    private lateinit var user: User
    lateinit var userName: String
    lateinit var activeUsers: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        SocketHandler.apply {
            setSocket()
            establishConnection()
            mSocket.emit(EVENT_CONNECT_ERROR, onConnectError)
            mSocket.emit(Socket.EVENT_CONNECT_TIMEOUT, onConnectError)
            mSocket.emit(Socket.EVENT_CONNECT, onConnect)
            mSocket.emit(Socket.EVENT_DISCONNECT, onDisconnect)
            mSocket.emit(ALLUSERS, allUsers)
            mSocket.emit("isActive", isActive)
            mSocket = getSocket()
        }
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val data: ArrayList<User> = ArrayList()
        val adapter = UserAdapter(this, data)
        rv.adapter = adapter

        addGroup.setOnClickListener {
            val i = Intent(
                this, ChatRoom::class.java
            )
            startActivity(i)
        }
        // userName = intent.extras.getString("username")
    }
    private val onConnectError = Emitter.Listener { }
    private val onDisconnect = Emitter.Listener { }

    private val onConnect = Emitter.Listener {
        val data = initialData(userName)
        val jsonData = gson.toJson(data)
        mSocket.emit("connection", jsonData)
    }
    private val allUsers = Emitter.Listener {
        val users = mutableListOf<User>()
        val jsonData = gson.toJson(users)
        mSocket.emit("AllUsers", jsonData)
    }

    var isActive = Emitter.Listener {
        val user = JSONObject()
        try {
            user.put("userId", "f")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        mSocket.emit("isActive", user)
        is_online.visibility = View.VISIBLE
    }
}
