package id.co.aygo.androidjetpack.view.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import id.co.aygo.androidjetpack.R
import id.co.aygo.androidjetpack.utils.adapter.RecyclerViewAdapter
import id.co.aygo.androidjetpack.view.base.AygoFragment
import id.co.aygo.androidjetpack.view.base.Constants.FRAGMENT_EMPLOYES
import id.co.aygo.androidjetpack.view.home.configTab.FragmentUtils.sendActionToActivity
import id.co.aygo.androidjetpack.view.home.model.MenuModel
import id.co.aygo.androidjetpack.view.home.holder.MenuHolder
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class HomeFragment : AygoFragment() {

    lateinit var adapter :RecyclerViewAdapter<*, *>

    override fun onView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        v.toolbar.title = getString(R.string.title_home)
        v.rMenu.layoutManager = GridLayoutManager(context, 3)
        v.rMenu.setHasFixedSize(true)

        adapter = object : RecyclerViewAdapter<MenuModel, MenuHolder>(R.layout.layout_menu_home, dataMenu(), MenuModel::class.java, MenuHolder::class.java){
            override fun bindView(holder: MenuHolder, model: MenuModel, position: Int) {
                holder.title.text = model.title
                holder.image.background = model.img
                holder.bg.setOnClickListener { onChooseMenu(position) }
            }

        }

        v.rMenu.adapter = adapter


        return v
    }

    private fun onChooseMenu(position: Int) {
        when(position){
            0-> sendActionToActivity(Bundle(),FRAGMENT_EMPLOYES, currentTabs, true, fragmentInteractionCallback!!)
        }

    }

    fun dataMenu(): ArrayList<MenuModel>{
       val data = ArrayList<MenuModel>()
        data.add(MenuModel("Employe", context!!.resources.getDrawable(R.drawable.ic_person_black_24dp)))
        data.add(MenuModel("Time Off", context!!.resources.getDrawable(R.drawable.ic_person_black_24dp)))
        data.add(MenuModel("Employe", context!!.resources.getDrawable(R.drawable.ic_person_black_24dp)))

        return data
    }
}
