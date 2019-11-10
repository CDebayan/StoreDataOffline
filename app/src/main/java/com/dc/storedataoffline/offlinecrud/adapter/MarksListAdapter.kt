package com.dc.storedataoffline.offlinecrud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dc.storedataoffline.ItemClickListener
import com.dc.storedataoffline.R
import com.dc.storedataoffline.offlinecrud.model.MarksModel
import com.dc.storedataoffline.offlinecrud.model.StudentModel
import kotlinx.android.synthetic.main.child_marks_list.view.*
import kotlinx.android.synthetic.main.child_student_list.view.*
import kotlinx.android.synthetic.main.child_student_list.view.root

class MarksListAdapter(private val marksList: List<MarksModel>, private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<MarksListAdapter.MarksListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarksListViewHolder {
        return MarksListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_marks_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return marksList.size
    }

    override fun onBindViewHolder(holder: MarksListViewHolder, position: Int) {
        holder.setDataToViews(position)
        holder.onClickListener()
    }

    inner class MarksListViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun setDataToViews(position: Int) {
            itemView.marks.text = "Marks : ${marksList[position].marks}"
            itemView.subject.text = "${marksList[position].subjectId}"
        }

        fun onClickListener() {
            itemView.root.setOnClickListener{
                itemClickListener.onItemClick(adapterPosition)
            }
        }

    }
}