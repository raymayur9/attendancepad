package com.dscnita.attendancetakingapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dscnita.attendancetakingapp.R
import com.dscnita.attendancetakingapp.entities.ClassItem
import com.dscnita.attendancetakingapp.entities.StudentItem

class StudentAdapter(
    private val context: Context,
    private val dataset: MutableList<StudentItem>,
    private val itemClickListener: (View, Int, Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder> (){

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rollNo: TextView =view.findViewById(R.id.rollNo)
        val studentName: TextView =view.findViewById(R.id.studentName)
        val status:TextView=view.findViewById(R.id.status)
        val card:CardView=view.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)

        val studentVH= StudentViewHolder(adapterLayout)
        studentVH.onClick(itemClickListener)
        return studentVH
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.rollNo.text = dataset[position].rollNo
        holder.studentName.text = dataset[position].name
        if(dataset[position].status) {
            holder.status.text = "P"
            holder.rollNo.setTextColor(ContextCompat.getColor(context,R.color.black))
            holder.studentName.setTextColor(ContextCompat.getColor(context,R.color.black))
            holder.status.setTextColor(ContextCompat.getColor(context,R.color.black))
        }
        else {
            holder.status.text = "A"
            holder.rollNo.setTextColor(ContextCompat.getColor(context,R.color.white))
            holder.studentName.setTextColor(ContextCompat.getColor(context,R.color.white))
            holder.status.setTextColor(ContextCompat.getColor(context,R.color.white))
        }
        holder.card.setCardBackgroundColor(getColor(holder.status.text as String))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    private fun <T : RecyclerView.ViewHolder> T. onClick(event: (view: View, position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(it, adapterPosition, itemViewType)
        }
        return this
    }

    private fun getColor(status:String):Int
    {
        return if(status=="P") {
            Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.green)))
        } else
            Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.red)))
    }

    fun updateStudentItems(updatedDataset:List<StudentItem>)
    {
        dataset.clear()
        dataset.addAll(updatedDataset)
        notifyDataSetChanged()
    }
}