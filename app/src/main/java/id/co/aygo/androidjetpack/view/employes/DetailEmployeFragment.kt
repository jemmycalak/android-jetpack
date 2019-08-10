package id.co.aygo.androidjetpack.view.employes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.co.aygo.androidjetpack.R
import id.co.aygo.androidjetpack.view.base.AygoFragment
import android.content.Intent
import kotlinx.android.synthetic.main.fragment_detail_employe.view.*
import android.net.Uri
import id.co.aygo.androidjetpack.view.employes.model.EmployeModel
import kotlinx.android.synthetic.main.layout_header_detailemploye.view.*
import kotlinx.android.synthetic.main.layout_scrolling_detail.view.*


class DetailEmployeFragment : AygoFragment() {

    companion object{
        fun initial(bundle: Bundle):DetailEmployeFragment {
            val detailEmployeFragment = DetailEmployeFragment()
            detailEmployeFragment.arguments = bundle
            return  detailEmployeFragment
        }

        val DATA_DETAILEMPLOYE = "data.employe"
    }

    fun getDataEmploye():EmployeModel{
        return arguments!!.getParcelable(DATA_DETAILEMPLOYE) as EmployeModel
    }

    override fun onView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v= inflater.inflate(R.layout.fragment_detail_employe, container, false)

        //constraintToolbar.transitionToEnd()
        v.fab_email.setOnClickListener { onEmail() }
        v.fab_telp.setOnClickListener { onCall() }

        initData(v)

        return v
    }

    private fun initData(v: View) {
        val data = getDataEmploye()
        v.name.text = data.name
        v.icon.setImageDrawable(context!!.resources.getDrawable(data.imageUrl))
        v.notelp.text = ": "+data.notelp
        v.email.text = ": "+data.email
        v.birthdate.text = ": "+data.birthDate
        v.since.text = ": "+data.birthDate
        v.jabatan.text = ": "+data.jabatan
        v.company.text = ": "+data.company.companyName
        v.atasan.text = ": "+data.atasan
        v.bawahan.text = ": "+data.bahawan.size.toString()

    }

    fun onEmail(){
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        sendIntent.type = "text/plain"
        //sendIntent.setPackage("com.whatsapp")
        startActivity(sendIntent)
    }

    fun onCall(){
        try {
            val number = Uri.parse("tel:" + "888;w")
            val dial = Intent(Intent.ACTION_CALL, number)
            startActivity(dial)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}
