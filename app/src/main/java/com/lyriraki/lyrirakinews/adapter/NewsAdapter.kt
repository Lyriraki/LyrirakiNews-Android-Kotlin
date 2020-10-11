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
import com.lyriraki.lyrirakinews.model.News


class ListNewsAdapter(private var listNews: ArrayList<News>) : RecyclerView.Adapter<ListNewsAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvJudul: TextView = itemView.findViewById(R.id.judul_news)
        var tvAuthor: TextView = itemView.findViewById(R.id.author_news)
        var tvDes: TextView = itemView.findViewById(R.id.des_news)
        var imgPhoto: ImageView = itemView.findViewById(R.id.foto_news)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.news_item_row, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val hero = listNews[position]
        Glide.with(holder.itemView.context)
            .load(hero.photo)
            .apply(RequestOptions().override(1200, 630))
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(holder.imgPhoto)

        holder.tvJudul.text = hero.judul
        holder.tvAuthor.text = hero.author
        holder.tvDes.text = hero.des
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listNews[holder.adapterPosition])}
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: News)
    }
}
