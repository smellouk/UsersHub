package io.mellouk.users_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.mellouk.common_android.domain.model.User
import kotlinx.android.synthetic.main.user_list_item.view.*

class UsersAdapter(
    private val onClick: (User) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {
    private val users = mutableListOf<User>()

    fun setUsers(list: List<User>) {
        users.clear()
        users.addAll(list)
        notifyDataSetChanged()
    }

    fun addUsers(list: List<User>) {
        with(users) {
            val startPosition: Int = users.size
            addAll(list)
            notifyItemRangeChanged(startPosition, size);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.user_list_item, parent, false
        ).apply {
            setOnClickListener {
                onClick((tag as User))
            }
        }
    )

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        users[position].let { user ->
            with(holder.itemView) {
                tag = user
                Picasso.get()
                    .load(user.avatar)
                    .placeholder(R.drawable.ic_account_circle_24dp)
                    .error(R.drawable.ic_warning_24dp)
                    .into(ivUser)
                tvUserName.text = user.name
            }
        }
    }
}

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view)