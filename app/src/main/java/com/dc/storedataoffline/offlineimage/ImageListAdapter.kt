package com.dc.storedataoffline.offlineimage

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dc.storedataoffline.R
import kotlinx.android.synthetic.main.child_image_list.view.*

class ImageListAdapter(val activity : Activity, val imageList : ArrayList<ImageModel>) : RecyclerView.Adapter<ImageListAdapter.ImageListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        return ImageListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.child_image_list,parent,false))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        holder.setDataToViews(position)
    }

    inner class ImageListViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun setDataToViews(position: Int) {
            Glide.with(activity).load(imageList[position].imageUrl).into(itemView.childImage)
        }

    }
}