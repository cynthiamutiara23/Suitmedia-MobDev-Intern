package com.suitmedia.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suitmedia.data.remote.response.User
import com.suitmedia.databinding.ItemUserBinding
import com.suitmedia.ui.screen.SecondScreenActivity

class UserAdapter: PagingDataAdapter<User, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: User) {
            binding.apply {
                val fullname = "${data.firstName} ${data.lastName}"
                tvItemName.text = fullname
                tvItemEmail.text = data.email
                Glide.with(root.context).load(data.avatar).into(imgItemPhoto)

                itemView.setOnClickListener {
                    val intent = Intent()
                    intent.putExtra(SecondScreenActivity.EXTRA_USER, fullname)
                    (itemView.context as Activity).setResult(Activity.RESULT_OK, intent)
                    (itemView.context as Activity).finish()
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}