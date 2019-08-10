package id.co.aygo.androidjetpack.view.employes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import id.co.aygo.androidjetpack.R
import id.co.aygo.androidjetpack.utils.adapter.RecyclerViewAdapter
import id.co.aygo.androidjetpack.view.base.AygoFragment
import id.co.aygo.androidjetpack.view.base.Constants.FRAGMENT_DETAIL_PROFILE
import id.co.aygo.androidjetpack.view.employes.DetailEmployeFragment.Companion.DATA_DETAILEMPLOYE
import id.co.aygo.androidjetpack.view.employes.holder.EmployeHolder
import id.co.aygo.androidjetpack.view.employes.model.EmployeModel
import id.co.aygo.androidjetpack.view.employes.model.Company
import kotlinx.android.synthetic.main.fragment_employe.view.*
import id.co.aygo.androidjetpack.view.home.configTab.FragmentUtils.sendActionToActivity

class EmployeFragment : AygoFragment() {

    lateinit var adapter:RecyclerViewAdapter<*,*>

    override fun onView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_employe, container, false)

        adapter = object : RecyclerViewAdapter<EmployeModel, EmployeHolder>(R.layout.layout_item_employe, dataEmployes(), EmployeModel::class.java, EmployeHolder::class.java){
            override fun bindView(holder: EmployeHolder, model: EmployeModel, position: Int) {
                holder.image.background = context!!.resources.getDrawable(model.imageUrl)
                holder.name.text = model.name
                holder.email.text = model.email
                holder.notelp.text = model.notelp
                holder.company.text = model.company.companyName

                holder.layout.setOnClickListener { onDetailEmnploye(model) }
            }
        }

        v.rEmployes.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        v.rEmployes.setHasFixedSize(true)

        v.rEmployes.adapter = adapter

        return v
    }

    private fun onDetailEmnploye(model: EmployeModel) {

        var bundle = Bundle()
        bundle.putParcelable(DATA_DETAILEMPLOYE, model)

        sendActionToActivity(
            bundle,
            FRAGMENT_DETAIL_PROFILE,
            currentTabs,
            true,
            fragmentInteractionCallback!!
        )
    }

    fun dataEmployes():ArrayList<EmployeModel>{
        val data = ArrayList<EmployeModel>()

        for (i in 1..20) {
            data.add(EmployeModel("", "Rocky Simanjuntak ", "jemmy.sapta14@gmail.com", "082269219485",
                    R.drawable.image_4, "Manager", "14-01-2020", "14-01-1995",
                    Company("", "Tokomodal Mitra Usaha"),
                    "Chris Antonius", listOf("bahawan 1", "bawahan 2", "bawahan 3")))
            data.add(EmployeModel("", "Neneng Situmorang", "jemmy.sapta14@gmail.com", "082269219485",
                    R.drawable.image_5, "Manager", "14-01-2020", "14-01-1995",
                    Company("", "Tokomodal Mitra Usaha"),
                    "Chris Antonius", listOf("bahawan 1", "bawahan 2", "bawahan 3")))
            data.add(EmployeModel("", "Calvin Situanjuk", "jemmy.sapta14@gmail.com", "082269219485",
                    R.drawable.image_6, "Manager", "14-01-2020", "14-01-1995",
                    Company("", "Tokomodal Mitra Usaha"),
                    "Chris Antonius", listOf("bahawan 1", "bawahan 2", "bawahan 3")))
            data.add(EmployeModel("", "Obama Nababan", "jemmy.sapta14@gmail.com", "082269219485",
                    R.drawable.image_7, "Manager", "14-01-2020", "14-01-1995",
                    Company("", "Tokomodal Mitra Usaha"),
                    "Chris Antonius", listOf("bahawan 1", "bawahan 2", "bawahan 3")))
        }


        return data
    }

}
