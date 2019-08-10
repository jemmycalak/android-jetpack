package id.co.aygo.androidjetpack.view.account


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.co.aygo.androidjetpack.R
import id.co.aygo.androidjetpack.view.base.AygoFragment


class AccountFragment : AygoFragment() {
    override fun onView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_account, container, false)

        Log.e("onCreate", "<<<<<<<<<<AccountFragmentFragment")

        return v
    }
}
