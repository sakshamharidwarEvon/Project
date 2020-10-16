package com.example.task_2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task_2.ModelClass.Companion.AD_LAYOUT
import com.example.task_2.ModelClass.Companion.IMAGE_LAYOUT
import com.example.task_2.ModelClass.Companion.USER_INFO_LAYOUT

class Adapter(
    private val modelClassList: List<ModelClass>,
    private val recyclerViewClickInterface: RecyclerViewClickInterface
) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private var lastPosition = -1


    override fun getItemViewType(position: Int): Int {
        return when (modelClassList[position].getViewType()) {
            0 -> USER_INFO_LAYOUT
            1 -> AD_LAYOUT
            2 -> IMAGE_LAYOUT
            else -> -1
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            USER_INFO_LAYOUT -> {
                val userInfoLayout = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.item_layout, viewGroup, false)
                UserInfoLayout(userInfoLayout)
            }
            AD_LAYOUT -> {
                val adLayout = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.ad_layout, viewGroup, false)
                AdLayout(adLayout)
            }
            IMAGE_LAYOUT -> {
                val imageLayout = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.image_layout, viewGroup, false)
                ImageLayout(imageLayout)
            }
            else -> ImageLayout(
                LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.image_layout, viewGroup, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (modelClassList[position].viewType) {
            USER_INFO_LAYOUT -> {
                val userImage: Int = modelClassList[position].imageResource
                val title: String = modelClassList[position].title.toString()
                val body: String = modelClassList[position].body.toString()
                Log.e("val1", "" + userImage)
                Log.e("val2", "" + title)
                Log.e("val3", "" + body)
                (holder as UserInfoLayout).setData(userImage, title, body)
            }
            AD_LAYOUT -> {
                val ad: String = modelClassList[position].adText.toString()
                Log.e("val4", "" + ad)
                (holder as AdLayout).setAdText(ad)
            }
            IMAGE_LAYOUT -> {
                val resource: Int = modelClassList[position].resource
                Log.e("val5", "" + resource)
                (holder as ImageLayout).setImageView(resource)
            }
            else -> return
        }
        if (lastPosition < position) {
            val animation =
                AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.fade_in)
            holder.itemView.animation = animation
            lastPosition = position
        }
    }

    override fun getItemCount(): Int {
        return modelClassList.size
    }

    internal inner class UserInfoLayout(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val userImage: ImageView
        private val username: TextView
        private val body: TextView
        fun setData(image: Int, name: String, bodyText: String) {
            userImage.setImageResource(image)
            username.text = name
            body.text = bodyText
        }

        override fun onClick(view: View) {
            recyclerViewClickInterface.onItemClick(adapterPosition)
        }

        init {
            userImage = itemView.findViewById(R.id.image_view)
            username = itemView.findViewById(R.id.title)
            body = itemView.findViewById(R.id.body)
            itemView.setOnClickListener { recyclerViewClickInterface.onTextClick(adapterPosition) }
        }
    }

    internal inner class AdLayout(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val adText: TextView
        fun setAdText(ad: String) {
            adText.text = ad
        }

        override fun onClick(view: View) {
            itemView.setOnClickListener { recyclerViewClickInterface.onTextClick(adapterPosition) }
        }

        init {
            adText = itemView.findViewById(R.id.ad)
            itemView.setOnClickListener { recyclerViewClickInterface.onItemClick(adapterPosition) }
        }
    }

    internal inner class ImageLayout(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView
        fun setImageView(image: Int) {
            imageView.setImageResource(image)
        }

        init {
            imageView = itemView.findViewById(R.id.image)
        }
    }
}