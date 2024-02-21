package com.example.chatapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BaseAdapterAdapter(var arrayList: ArrayList<BaseModel>)  : BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var initView = LayoutInflater.from(parent?.context).inflate(R.layout.spinner_item, parent,false)
        var tvTitle = initView.findViewById<TextView>(R.id.tvTitle)
        tvTitle.setText(arrayList[position].name)
        return initView
    }
    }


class RecyclerClass(var list : ArrayList<MessageDataClass>) : RecyclerView.Adapter<RecyclerClass.ViewHolder>() {
    class ViewHolder(var views: View): RecyclerView.ViewHolder(views) {
        var tvMessage = views.findViewById<TextView>(R.id.tvMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        lateinit var view : View
        if(viewType == 0){
            view = LayoutInflater.from(parent.context).inflate(R.layout.layout_right, parent, false)
        }else{
            view = LayoutInflater.from(parent.context).inflate(R.layout.layout_left, parent, false)

        }
        return ViewHolder(view)
    }


    override fun getItemViewType(position: Int): Int {
//        return if(position%2 == 0)
//            0
//        else 1
//        return super.getItemViewType(position)
        return if(list[position].id == 0){
            0
        }else 1
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //set values here
        holder.tvMessage.setText(list[position].message)
    }
}
