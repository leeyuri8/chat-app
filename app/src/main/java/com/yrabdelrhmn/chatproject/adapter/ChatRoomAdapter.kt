package com.yrabdelrhmn.chatproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yrabdelrhmn.chatproject.R
import com.yrabdelrhmn.chatproject.constant.Keys
import com.yrabdelrhmn.chatproject.models.Message

class ChatRoomAdapter(val context: Context, private val chatList: ArrayList<Message>) :
    RecyclerView.Adapter<ChatRoomAdapter.ViewHolder>() {
    val TAG = Keys.TAG
    private val CHAT_MINE = 0
    private val CHAT_PARTENER = 1
    private val USER_JOIN = 2
    private val USER_LEAVE = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomAdapter.ViewHolder {
        Log.d("chatList size", chatList.size.toString())
        var view: View? = null
        when (viewType) {
            CHAT_MINE -> {
                view = LayoutInflater.from(context)
                    .inflate(R.layout.send_message_layout, parent, false)
                Log.d("user inflating", "viewType : $viewType")
            }
            CHAT_PARTENER -> {
                view = LayoutInflater.from(context)
                    .inflate(R.layout.receive_message_layout, parent, false)
                Log.d("partner inflating", "viewType : $viewType")
            }
            USER_JOIN -> {
                view = LayoutInflater.from(context)
                    .inflate(R.layout.chat_into_notification, parent, false)
                Log.d("someone in or out", "viewType : $viewType")
            }
            USER_LEAVE -> {
                view = LayoutInflater.from(context)
                    .inflate(R.layout.chat_into_notification, parent, false)
                Log.d("someone in or out", "viewType : $viewType")
            }
        }
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ChatRoomAdapter.ViewHolder, position: Int) {
        val messageData = chatList[position]

        val userName = messageData.messageContent
        val content = messageData.messageContent
        val viewType = messageData.type

        when (viewType) {
            CHAT_MINE -> {
                holder.message.text = content
            }
            CHAT_PARTENER -> {
                holder.userName.text = userName
                holder.message.text = content
            }
            USER_JOIN -> {
                val text = "$userName has entered the room"
                holder.text.text = text
            }
            USER_LEAVE -> {
                val text = "$userName has left the room"
                holder.text.text = text
            }
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
    override fun getItemViewType(position: Int): Int {
        return chatList[position].type
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.chat_username)
        val message: TextView = itemView.findViewById(R.id.messageBody)
        val text: TextView = itemView.findViewById(R.id.text)
    }
}
