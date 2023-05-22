package org.d3if0147.hitungbiayawarnet.ui.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if0147.hitungbiayawarnet.databinding.ListItemBinding
import org.d3if0147.hitungbiayawarnet.model.Game

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
            imageView.setImageResource(game.imageResId)


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