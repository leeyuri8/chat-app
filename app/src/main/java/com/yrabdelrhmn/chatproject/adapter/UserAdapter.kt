package com.yrabdelrhmn.chatproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yrabdelrhmn.chatproject.R
import com.yrabdelrhmn.chatproject.models.User
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(
    val context: Context,
    var data: ArrayList<User>,
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.user_name
        val userImg: ImageView = itemView.user_img
        var isOnline: ImageView = itemView.is_online
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = data[position]
        holder.userName.text = currentItem.userName
        holder.userImg.setImageResource(currentItem.image)
        holder.isOnline
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
