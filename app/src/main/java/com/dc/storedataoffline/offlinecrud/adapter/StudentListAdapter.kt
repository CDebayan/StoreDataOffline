package com.dc.storedataoffline.offlinecrud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dc.storedataoffline.ItemClickListener
import com.dc.storedataoffline.R
import com.dc.storedataoffline.offlinecrud.model.StudentModel
import kotlinx.android.synthetic.main.child_student_list.view.*

class StudentListAdapter(private val studentsList: List<StudentModel>,private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<StudentListAdapter.StudentListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListViewHolder {
        return StudentListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.child_student_list,parent,false))
    }

    override fun getItemCount(): Int {
        return studentsList.size
    }

    override fun onBindViewHolder(holder: StudentListViewHolder, position: Int) {
        holder.setDataToViews(position)
        holder.onClickListener()
    }

    inner class StudentListViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun setDataToViews(position: Int) {
            itemView.name.text = studentsList[position].studentsName
            itemView.roll.text = "Roll : ${studentsList[position].studentsRoll}"
            itemView.className.text = studentsList[position].className
        }

        fun onClickListener() {
            itemView.root.setOnClickListener{
                itemClickListener.onItemClick(adapterPosition)
            }
        }

    }
}