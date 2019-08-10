package id.co.aygo.androidjetpack.view.home.holder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_menu_home.view.*

class MenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title :TextView
    var image : ImageView
    var bg : LinearLayout
    init {
        title = itemView.tMenu
        image = itemView.icMenu
        bg = itemView.bgMenu
    }
}