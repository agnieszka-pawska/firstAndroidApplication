package com.example.firstapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private var users: MutableList<User>
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    lateinit var mRecyclerView: RecyclerView

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val userNameView: TextView = view.findViewById(R.id.user_name)
        private val userEmailView: TextView = view.findViewById(R.id.user_email)

        fun bind(name: String, email: String) {
            userNameView.text = name
            userEmailView.text = email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mRecyclerView = parent.findViewById<View>(R.id.recycler_view) as RecyclerView

        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)

        val deleteUserButton: Button =  itemView.findViewById(R.id.delete_user_button)
        deleteUserButton.setOnClickListener {
            remove(itemView)
        }
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position].name, users[position].email)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun add(user: User) {
        users.add(user)
        notifyItemInserted(users.size - 1)
    }

    private fun remove(itemView: View) {
        val childAdapterPosition = mRecyclerView.getChildAdapterPosition(itemView)
        users.removeAt(childAdapterPosition)
        notifyItemRemoved(childAdapterPosition)
    }

}