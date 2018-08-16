package com.solucionesjyd.chatfirebase

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView

class userListAdapter(private var activity: Activity, private var items: ArrayList<userDatos>) : BaseAdapter(), ListAdapter {

    private class ViewHolder(row: View?) {
        var textnombre: TextView? = null
        var textcomentario: TextView? = null
        var textfecha: TextView? = null
        var imgAvatar: ImageView? = null

        init {
            this.textnombre = row?.findViewById<TextView>(R.id.textnombre)
            this.textcomentario = row?.findViewById<TextView>(R.id.textcomentario)
            this.textfecha = row?.findViewById<TextView>(R.id.textfecha)
            this.imgAvatar = row?.findViewById<ImageView>(R.id.imgAvatar)
        }

    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (p1 == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.user_list_row, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder

        } else {
            view = p1
            viewHolder = view.tag as ViewHolder

        }

        var userDatos = items[p0]
        viewHolder.textnombre?.text = userDatos.name
        viewHolder.textcomentario?.text = userDatos.mensaje
        viewHolder.textfecha?.text = userDatos.fecha
        if (userDatos.avatar == "osito") {
            viewHolder.imgAvatar?.setImageResource(R.drawable.osito)
        } else if (userDatos.avatar == "person") {
            viewHolder.imgAvatar?.setImageResource(R.drawable.person)
        } else if (userDatos.avatar == "gatito") {
            viewHolder.imgAvatar?.setImageResource(R.drawable.gatito)
        } else {
            viewHolder.imgAvatar?.setImageResource(R.drawable.perrito)
        }

        return view as View
    }

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }


}