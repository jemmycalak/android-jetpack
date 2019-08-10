package id.co.aygo.androidjetpack.utils.adapter


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.lang.reflect.Constructor
import java.util.ArrayList

abstract class RecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(val mLayout: Int, val mData: ArrayList<T>, val mModel: Class<T>, val mViewHolder: Class<VH>) : RecyclerView.Adapter<VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val viewGroup = LayoutInflater.from(parent.context).inflate(mLayout, parent, false) as ViewGroup
        try {
            val constructor = mViewHolder.getConstructor(View::class.java) as Constructor<VH>
            return constructor.newInstance(viewGroup)
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException(e.message + " onCreatViewHolder")
        }

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val model = mData[position]
        bindView(holder, model, position)
    }

    protected abstract fun bindView(holder: VH, model: T, position: Int)

    override fun getItemCount(): Int {
        return mData.size
    }

    fun OnUpdate(dataUpdate: ArrayList<T>) {
        for (model in dataUpdate) {
            mData.add(model)
            notifyDataSetChanged()
        }
    }

    fun addItem(position: Int, model: T) {
        mData.add(position, model)
        notifyItemInserted(position)
    }
}