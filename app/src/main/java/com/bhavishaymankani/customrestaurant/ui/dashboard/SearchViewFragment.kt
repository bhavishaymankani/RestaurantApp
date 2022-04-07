package com.bhavishaymankani.customrestaurant.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.customrestaurant.base.BaseFragment
import com.bhavishaymankani.customrestaurant.databinding.FragmentSearchviewBinding
import com.bhavishaymankani.customrestaurant.recyclerviewadapters.SearchItemsAdapter

class SearchViewFragment : BaseFragment(), SearchView.OnQueryTextListener {

    private lateinit var searchViewViewModel: SearchViewViewModel
    private var _binding: FragmentSearchviewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.title = "Search"

        searchViewViewModel =
            ViewModelProvider(this).get(SearchViewViewModel::class.java)

        _binding = FragmentSearchviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val searchView = binding.itemsSv
        val itemsRv = binding.searchItemsRv

        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

        searchViewViewModel.items.observe(viewLifecycleOwner) {
            createRecyclerview(requireContext().applicationContext, itemsRv, SearchItemsAdapter(it.items, searchViewViewModel) as RecyclerView.Adapter<RecyclerView.ViewHolder>)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { searchViewViewModel.searchItems(it) }
        return true
    }
}