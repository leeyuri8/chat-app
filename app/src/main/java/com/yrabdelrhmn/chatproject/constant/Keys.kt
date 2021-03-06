package com.yrabdelrhmn.chatproject.constant
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.yrabdelrhmn.chatproject.R
import com.yrabdelrhmn.chatproject.models.User
object Keys {
    // user and group keys
    const val TAG = "TTT"
    const val USER = "user"
    const val START = "start"
    const val ID = "id"
    const val NAME = "username"
    const val GROUPNAME = "groupname"

    // Server side keys
    val JOIN = "join"
    val ALLUSERS = "ALLUsers"
    val GROUPS = "Group"
    val ALLGROUPS = "AllGroup"
    val UPDATEUSER = "UpdateUser"

    // message keys
    val TEXT = "Text"
    val IMAGE = "image"
    val MESSAGE = "message"

    val SOURCE_ID = "source_id"
    val DES_ID = "des_id"
    val TYPE = "type"
    val ISONLINE = "isOnline"

    fun getSharePref(context: Context) =
        context.getSharedPreferences("Share", Activity.MODE_PRIVATE)
    fun editor(context: Context) = getSharePref(context).edit()
    fun getUser(context: Context): User = Gson().fromJson(getSharePref(context).getString(USER, "").toString(), User::class.java)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setUpStatusBar(activity: Activity, types: Int) {
        val window: Window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        when (types) {
            1 -> window.statusBarColor = ContextCompat.getColor(activity, R.color.white)
            else -> window.statusBarColor = ContextCompat.getColor(activity, R.color.purple_700)
        }
    }

    fun <T> removeDuplicates(list: ArrayList<T>): ArrayList<T>? {
        val newList = ArrayList<T>()
        // Traverse through the first list
        for (element in list) {
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
                newList.add(element)
            }
        }
        // return the new list
        return newList
    }
}
