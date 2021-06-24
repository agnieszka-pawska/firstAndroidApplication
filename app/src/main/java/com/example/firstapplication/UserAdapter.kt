package com.example.firstapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private val onRemoveUser: (userId: String) -> Unit,
    private val onClick: (user: User) -> Unit
): ListAdapter<User, UserAdapter.ViewHolder>(UsersDiffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val userNameView: TextView = view.findViewById(R.id.user_name)
        private val userEmailView: TextView = view.findViewById(R.id.user_email)
        val deleteUserButton: Button = itemView.findViewById(R.id.delete_user_button)

        fun bind(name: String, email: String) {
            userNameView.text = name
            userEmailView.text = email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user.name, user.email)

        holder.deleteUserButton.setOnClickListener {
            onRemoveUser(user.id)
        }

        holder.itemView.setOnClickListener {
            onClick(user)
        }
    }

    fun notifyDataSetChanged(usersList: List<User>) {
        submitList(usersList)
    }
}

object UsersDiffCallback: DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}