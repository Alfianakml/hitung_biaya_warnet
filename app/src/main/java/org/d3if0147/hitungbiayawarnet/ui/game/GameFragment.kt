package org.d3if0147.hitungbiayawarnet.ui.game

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.d3if0147.hitungbiayawarnet.MainActivity
import org.d3if0147.hitungbiayawarnet.R
import org.d3if0147.hitungbiayawarnet.data.SettingsDataStore
import org.d3if0147.hitungbiayawarnet.data.dataStore
import org.d3if0147.hitungbiayawarnet.databinding.FragmentGameBinding
import org.d3if0147.hitungbiayawarnet.network.ApiStatus


class GameFragment : Fragment() {

    private val LayoutDataStore: SettingsDataStore by lazy {
        SettingsDataStore(requireContext().dataStore)
    }

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this)[GameViewModel::class.java]
    }
    private lateinit var binding: FragmentGameBinding
    private lateinit var myAdapter: GameAdapter
    private var isLinearLayout = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentGameBinding.inflate(layoutInflater,container, false)
        myAdapter = GameAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context,
                    RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    private fun setLayout(){
        binding.recyclerView.layoutManager = if (isLinearLayout)
            LinearLayoutManager(context)
        else
            GridLayoutManager(context,2)
    }
    private fun setIcon(menuItem: MenuItem){
        val iconId = if (isLinearLayout)
            R.drawable.baseline_grid_view_24
        else
            R.drawable.baseline_view_list_24
        menuItem.icon = ContextCompat.getDrawable(requireContext(), iconId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LayoutDataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner){
            isLinearLayout = it
            setLayout()
            activity?.invalidateOptionsMenu()
        }

        viewModel.getData().observe(viewLifecycleOwner){
            myAdapter.updateData(it)
        }
        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }
        viewModel.scheduleUpdater(requireActivity().application)

    }
    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestNotificationPermission()
                }
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)

        val menuItem = menu.findItem(R.id.action_switch_layout)
        setIcon(menuItem)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_switch_layout){
            lifecycleScope.launch {
                LayoutDataStore.saveLayout(!isLinearLayout, requireContext())
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                MainActivity.PERMISSION_REQUEST_CODE
            )
        }
    }

}