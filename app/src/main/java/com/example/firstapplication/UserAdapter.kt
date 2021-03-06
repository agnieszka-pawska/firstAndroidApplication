package com.example.firstapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private var users: MutableList<User>,
    private val removeUserFromViewModel: (userId: String) -> Unit
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

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
        holder.bind(users[position].name, users[position].email)

        val deleteUserButton: Button =  holder.deleteUserButton
        deleteUserButton.setOnClickListener {
            remove(users[position].id)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    private fun remove(userId: String) {
        removeUserFromViewModel(userId)
    }

    fun notifyDataSetChanged(usersList: List<User>) {
        users = usersList.toMutableList()
        notifyDataSetChanged()
    }
}