package org.d3if0147.hitungbiayawarnet.ui.histori

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0147.hitungbiayawarnet.R
import org.d3if0147.hitungbiayawarnet.databinding.ItemHistoriBinding
import org.d3if0147.hitungbiayawarnet.db.WarnetDao
import org.d3if0147.hitungbiayawarnet.db.WarnetDb
import org.d3if0147.hitungbiayawarnet.db.WarnetEntity
import org.d3if0147.hitungbiayawarnet.model.hitungPemakaian
import java.util.*

class HistoriAdapter :
androidx.recyclerview.widget.ListAdapter<WarnetEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<WarnetEntity>() {
                override fun areItemsTheSame(
                    oldData: WarnetEntity, newData: WarnetEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: WarnetEntity, newData: WarnetEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), holder.itemView)
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: WarnetEntity, view: View) = with(binding){
            val hasilHitung = item.hitungPemakaian()
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            userTextView.text = root.context.getString(R.string.user_x, hasilHitung.username)
            passTextView.text = root.context.getString(R.string.pass_x, hasilHitung.password)
            jamTextView.text = root.context.getString(R.string.jam_x, hasilHitung.jam.toString())
            tipeTextView.text = root.context.getString(R.string.tipe_x, item.tipe.toString())
            binding.button.setOnClickListener { hapusData(item.id, view.context) }
        }
        private fun hapusData(id: Long, context: Context) {
            val db = WarnetDb.getInstance(context)
            val WarnetDao = db.dao
            MaterialAlertDialogBuilder(context)
                .setMessage(context.getString(R.string.konfirmasi_hapus))
                .setPositiveButton(context.getString(R.string.hapus)) { _, _ ->
                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.IO) {
                            WarnetDao.deleteHistory(id)
                        }
                    }
                }
                .setNegativeButton(context.getString(R.string.batal)) { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }
    }

}
