package com.lyriraki.lyrirakinews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lyriraki.lyrirakinews.R
import com.lyriraki.lyrirakinews.model.Author

class ListAuthorNewsAdapter(private var listAuthor: ArrayList<Author>) : RecyclerView.Adapter<ListAuthorNewsAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvByAuthor: TextView = itemView.findViewById(R.id.by_authorname_news)
        var imgPhotobyAuthor: ImageView = itemView.findViewById(R.id.by_authorphoto_news)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.author_item_row, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listAuthor.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val author = listAuthor[position]
        Glide.with(holder.itemView.context)
            .load(author.authorPhoto)
            .apply(RequestOptions().override(1200, 630))
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(holder.imgPhotobyAuthor)
        holder.tvByAuthor.text = author.authorName
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAuthor[holder.adapterPosition])}
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Author)
    }
}