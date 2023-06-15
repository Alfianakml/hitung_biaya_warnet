package org.d3if0147.hitungbiayawarnet.ui.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0147.hitungbiayawarnet.R
import org.d3if0147.hitungbiayawarnet.databinding.ListItemBinding
import org.d3if0147.hitungbiayawarnet.model.Game
import org.d3if0147.hitungbiayawarnet.network.GameApi

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>(){
    private val data = mutableListOf<Game>()

    fun updateData(newData: List<Game>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(game: Game) = with(binding){
            nameTextView.text = game.nama
            publisherTextView.text= game.publisher
            Glide.with(imageView.context)
                .load(GameApi.getGameUrl(game.imageResId))
                .error(R.drawable.baseline_broken_image_24)
                .into(imageView)



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}