package id.co.aygo.androidjetpack.view.employes.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_employe.view.*

class EmployeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image = itemView.img_employe
    val name = itemView.name_employe
    var notelp = itemView.notelp_employe
    var email = itemView.email_employe
    var company = itemView.company_employe
    var layout= itemView.layout

}
