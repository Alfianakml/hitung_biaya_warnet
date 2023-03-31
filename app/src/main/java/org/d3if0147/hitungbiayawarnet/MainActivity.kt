package org.d3if0147.hitungbiayawarnet


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if0147.hitungbiayawarnet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnHitung.setOnClickListener { hitung() }

    }

    private fun hitung(){
        val jam = binding.jamInp.text.toString()

        if (TextUtils.isEmpty(jam)) {
            Toast.makeText(this, R.string.jam_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedValue = binding.tipeSpinner.selectedItem.toString()
        if(selectedValue == "Regular"){
            val reg = 5000
            val hasilRegular = jam.toInt() * reg
            binding.bayarTxtview.text = getString(R.string.harga_x, hasilRegular)
        }else if (selectedValue == "VIP"){
            val vip = 7000
            val hasilVip = jam.toInt() * vip
            binding.bayarTxtview.text = getString(R.string.harga_x, hasilVip)
        }else if(selectedValue == "VVIP"){
            val vvip = 9000
            val hasilvvip = jam.toInt() * vvip
            binding.bayarTxtview.text = getString(R.string.harga_x, hasilvvip)
        }
    }
}