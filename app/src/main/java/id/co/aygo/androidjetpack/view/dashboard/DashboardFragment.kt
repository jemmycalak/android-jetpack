package id.co.aygo.androidjetpack.view.dashboard


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.co.aygo.androidjetpack.R
import id.co.aygo.androidjetpack.view.base.AygoFragment

class DashboardFragment : AygoFragment() {
    override fun onView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_dashboard, container, false)


        Log.e("onCreate", "<<<<<<<<<<DashboardFragment")

        return v
    }
}
