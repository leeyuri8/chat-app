package com.yrabdelrhmn.chatproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.Gson
import com.yrabdelrhmn.chatproject.adapter.ChatRoomAdapter
import com.yrabdelrhmn.chatproject.constant.Keys
import com.yrabdelrhmn.chatproject.models.Message
import com.yrabdelrhmn.chatproject.models.MessageType
import com.yrabdelrhmn.chatproject.models.initialData
import com.yrabdelrhmn.chatproject.server.SocketHandler
import kotlinx.android.synthetic.main.chat_room_activity.*
import org.json.JSONException
import org.json.JSONObject

class ChatRoom : AppCompatActivity(), View.OnClickListener {
    val TAG = Keys.TAG
    private lateinit var mSocket: Socket
    private var userId = Keys.DES_ID
    lateinit var userName: String
    lateinit var roomName: String
    private var chatName: String? = null

    var typing = false
    val gson: Gson = Gson()
    private val chatList: ArrayList<Message> = arrayListOf()
    lateinit var chatRoomAdapter: ChatRoomAdapter
    lateinit var mTypingHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_room_activity)
        chatRoomAdapter = ChatRoomAdapter(this, chatList)
        recyclerView.adapter = chatRoomAdapter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager



        send.setOnClickListener {
            sendMessage()
        }
        leave.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
        }

        SocketHandler.apply {
            mSocket.connect()
            mSocket.on("join", onNewUser)
            mSocket.on("userLeftChat", onUserLeft)
            //  mSocket.on("isActive",isActive)
            mSocket.on("typing", onTyping)
        }
    }

    private fun sendMessage() {
        val message = JSONObject()
        message.put("message", mess.text.toString())
        message.put("des_id", "1") // من ال active users
        message.put("source_id ", userId)
        mSocket.emit("message", message)
        mess.setText("")
    }

    private var onUserLeft = Emitter.Listener {
        val leftUserName = it[0] as String
        val chat: Message = Message("", leftUserName, "", MessageType.USER_LEAVE.index)
    }

    var onTyping = Emitter.Listener {
        val data = JSONObject()
        data.put("username", userName)
        mSocket.emit("typing", data)
    }

    var onUpdateChat = Emitter.Listener {
        val chat: Message = gson.fromJson(it[0].toString(), Message::class.java)
        chat.type = MessageType.CHAT_RECEIVER.index
        addItemToRecyclerView(chat)
    }

    var onNewUser = Emitter.Listener {
        val name = it[0] as String
        val chat = Message("", name, "", MessageType.USER_JOIN.index)
    }

    var isActive = Emitter.Listener {
        val user = JSONObject()
        try {
            user.put("userId", "d")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        mSocket.emit("isActive", user)
    }

    private fun addItemToRecyclerView(message: Message) {
        runOnUiThread {
            chatList.add(message)
            chatRoomAdapter.notifyItemInserted(chatList.size)
            mess.setText("")
            recyclerView.scrollToPosition(chatList.size - 1)
        }
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.img_send -> sendMessage()
            R.id.chat_back_arrow -> onDestroy()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        val data = initialData(userName)
        val jsonData = gson.toJson(data)
        mSocket.emit("disconnect", jsonData)
        SocketHandler.closeConnection()
    }
}
