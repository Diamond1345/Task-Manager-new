package com.example.task_manager_new.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.task_manager_new.databinding.ItemTaskBinding
import com.example.task_manager_new.model.Task

class TaskAdapter(private val onLongClick: (Task) -> Unit):Adapter<TaskAdapter.TaskViewHolder>() {
    private val data = arrayListOf<Task>()

    fun addTasks(list: List<Task>){
        data.clear()
        data.addAll(list)
        data.sortByDescending { it.id }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding): ViewHolder(binding.root){
        fun bind(task: Task){
            binding.tvTitle.text = task.title
            binding.tvDesk.text = task.desc

            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }
        }
    }

}



