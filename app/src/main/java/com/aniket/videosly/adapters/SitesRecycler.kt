package com.aniket.videosly.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aniket.videosly.activities.BrowserActivity
import com.aniket.videosly.R
import com.aniket.videosly.SitesData
import kotlinx.android.synthetic.main.sites_row.view.*

class SitesRecycler(val context:Context,val list:List<SitesData>): RecyclerView.Adapter<SitesRecycler.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.sites_row, parent, false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item=list[position]

        holder.tvtitle.text=item.name
        holder.logo.setImageResource(item.logo)

        holder.itemView.setOnClickListener {
            val i=Intent(context, BrowserActivity::class.java)
            i.putExtra("url",item.url)
            context.startActivity(i)
        }
    }


    class ViewHolder(v:View):RecyclerView.ViewHolder(v){
            val tvtitle=v.tvTitle
            val logo=v.imgLogo
    }

}