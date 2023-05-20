package org.d3if0147.hitungbiayawarnet


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if0147.hitungbiayawarnet.databinding.FragmentMainBinding
import org.d3if0147.hitungbiayawarnet.model.HasilHitung
import kotlin.random.Random

class FragmentMain : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnHitung.setOnClickListener { hitungWarnet() }
        viewModel.getUserPassWarnet().observe(requireActivity()) {showResult(it)}
    }

    private fun hitungWarnet() {
        val jam = binding.jamInp.text.toString()

        if (TextUtils.isEmpty(jam)) {
            Toast.makeText(context, R.string.jam_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedValue = binding.tipeSpinner.selectedItem.toString()

        viewModel.tampungWarnet(jam.toInt(), selectedValue)
    }

    fun showResult(result: HasilHitung?){
        if (result == null) return
        binding.userTxtview.text = "Username : " + result.username
        binding.passTxtview.text = "Password : " + result.password
        binding.bayarTxtview.text = getString(R.string.harga_x, result.harga)

    }

}


//        if (selectedValue == "Regular") {
//            val reg = 5000
//            val hasilRegular = jam.toInt() * reg
//            binding.bayarTxtview.text = getString(R.string.harga_x, hasilRegular)
//        } else if (selectedValue == "VIP") {
//            val vip = 7000
//            val hasilVip = jam.toInt() * vip
//            binding.bayarTxtview.text = getString(R.string.harga_x, hasilVip)
//        } else if (selectedValue == "VVIP") {
//            val vvip = 9000
//            val hasilvvip = jam.toInt() * vvip
//            binding.bayarTxtview.text = getString(R.string.harga_x, hasilvvip)
//        }
//
//        val randomuser = generateRandomUser(4)
//        val randompw = generateRandomPassword(6)
//
//        binding.userTxtview.text = "Username : $randomuser"
//        binding.passTxtview.text = "Password : $randompw"


//    fun generateRandomUser(length: Int): String {
//        val charPool: List<Char> =
//            ('A'..'Z') + ('A'..'Z') // Define the characters to choose from
//        return (1..length)
//            .map { Random.nextInt(0, charPool.size) }
//            .map(charPool::get)
//            .joinToString("")
//    }
//    fun generateRandomPassword(length: Int): String {
//        val charPool: List<Char> =
//            ('A'..'Z') + ('0'..'9') // Define the characters to choose from
//        return (1..length)
//            .map { Random.nextInt(0, charPool.size) }
//            .map(charPool::get)
//            .joinToString("")
//    }