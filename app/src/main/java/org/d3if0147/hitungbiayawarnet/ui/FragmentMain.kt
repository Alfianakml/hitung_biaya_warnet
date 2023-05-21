package org.d3if0147.hitungbiayawarnet.ui


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0147.hitungbiayawarnet.R
import org.d3if0147.hitungbiayawarnet.databinding.FragmentMainBinding
import org.d3if0147.hitungbiayawarnet.model.HasilHitung

class FragmentMain : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_fragmentMain_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnHitung.setOnClickListener { hitungWarnet() }
        binding.shareButton.setOnClickListener { shareData() }
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

    private fun shareData(){
        val message = getString(R.string.bagikan_template,
            binding.userTxtview.text,
            binding.passTxtview.text,
            binding.bayarTxtview.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
    fun showResult(result: HasilHitung?){
        if (result == null) return
        binding.userTxtview.text = "Username : " + result.username
        binding.passTxtview.text = "Password : " + result.password
        binding.bayarTxtview.text = getString(R.string.harga_x, result.harga)
        binding.shareButton.visibility = View.VISIBLE



    }

}
