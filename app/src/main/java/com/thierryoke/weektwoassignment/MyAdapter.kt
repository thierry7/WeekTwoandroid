package com.thierryoke.weektwoassignment

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter (val dataSet: List<MusicItem>,
                 private val clickInterface: ClickInterface): RecyclerView.Adapter<MyAdapter.MovieViewHolder>(){

    class MovieViewHolder( musicView : View ): RecyclerView.ViewHolder (musicView){

        private val pict: ImageView = musicView.findViewById(R.id.iv_image_music)
        private val title: TextView = musicView.findViewById(R.id.tv_title_music)
        private val album : TextView = musicView.findViewById(R.id.tv_album_music)
        private  val price: TextView = musicView.findViewById(R.id.tv_price_music)


        fun onBind(musicItem: MusicItem){

            Picasso.get().load(musicItem.artworkUrl60).into(pict)
            title.text = musicItem.artistName
            album.text = musicItem.collectionName
            price.text = musicItem.trackPrice.toString() +"USD"

        }


    }
    fun getList(): List<MusicItem>{
        return dataSet
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false))

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(dataSet[position])
        holder.itemView.setOnClickListener {
           clickInterface.onCellClickListener(position)


        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
