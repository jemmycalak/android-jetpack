package id.co.aygo.androidjetpack.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.co.aygo.androidjetpack.view.home.listener.FragmentInteractionCallback
import java.lang.ClassCastException

abstract class AygoFragment : Fragment() {

    companion object{
        lateinit var currentTabs:String
    }

    protected var fragmentInteractionCallback:FragmentInteractionCallback?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return onView(inflater, container, savedInstanceState)
    }

    abstract fun onView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            fragmentInteractionCallback = context as FragmentInteractionCallback
        }catch (e:ClassCastException){
            throw RuntimeException(context.toString()+" must implement "+FragmentInteractionCallback::class.java)
        }
    }
}