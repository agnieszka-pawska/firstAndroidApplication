package com.example.firstapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter: ListAdapter<ToDo, ToDoListAdapter.ViewHolder>(ToDoListDiffCallback) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val toDoItemCheckbox: CheckBox = view.findViewById(R.id.todo_item_checkbox)
        private val toDoItemTextView: TextView = view.findViewById(R.id.todo_item_text_view)

        fun bind(description: String, isCompleted: Boolean) {
            toDoItemTextView.text = description
            toDoItemCheckbox.isChecked = isCompleted
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.toDoItemCheckbox.setOnCheckedChangeListener { _, isChecked ->
            item.completed = isChecked
        }
        holder.bind(item.title ?: "", item.completed)
    }

    fun notifyDataSetChanged(toDoList: List<ToDo>) {
        submitList(toDoList)
    }
}

object ToDoListDiffCallback: DiffUtil.ItemCallback<ToDo>() {

    override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem == newItem
    }
}