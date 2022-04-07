package com.bhavishaymankani.customrestaurant.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bhavishaymankani.customrestaurant.CartActivity
import com.bhavishaymankani.customrestaurant.LoginActivity
import com.bhavishaymankani.customrestaurant.base.BaseFragment
import com.bhavishaymankani.customrestaurant.databinding.FragmentHomeBinding
import com.bhavishaymankani.customrestaurant.datasource.local.CartItemDatabase
import com.bhavishaymankani.customrestaurant.datasource.models.User
import com.bhavishaymankani.customrestaurant.recyclerviewadapters.CategoryAdapter
import com.bhavishaymankani.customrestaurant.recyclerviewadapters.ItemsAdapter
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_DATA
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_DATA_KEY
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_EMAIL_KEY
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_FIRSTNAME_KEY
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_ID_KEY
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_LASTNAME_KEY
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_MOB_NUMBER_KEY

class HomeFragment : BaseFragment(), OnCategoryClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private var category = "Pizza"
    private lateinit var list: MutableList<String>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val cartItemRepository = CartItemRepository(context?.let { CartItemDatabase(it) } ?: null)
        val cartItemRepository =
            CartItemRepository(CartItemDatabase(requireActivity().application.applicationContext))
        val factory = HomeViewModelFactory(cartItemRepository)

        homeViewModel =
            ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val arr = intArrayOf(R.drawable.pizza)
//        binding.carouselView.pageCount = arr.size
//        binding.carouselView.setImageListener { position, imageView ->
//            imageView.setImageResource(arr[position])
//        }

        list = mutableListOf<String>("Combo", "Appetizers", "Pasta", "Pizza", "Beverage")

        val categoryRecyclerView = binding.categoryRv

        createRecyclerview(
            requireContext(),
            categoryRecyclerView,
            CategoryAdapter(list, this) as RecyclerView.Adapter<RecyclerView.ViewHolder>,
            true
        )

        val itemsRecyclerView = binding.itemsRv


        homeViewModel.items.observe(viewLifecycleOwner) {
            createRecyclerview(
                requireContext(),
                itemsRecyclerView,
                ItemsAdapter(
                    it.items,
                    homeViewModel
                ) as RecyclerView.Adapter<RecyclerView.ViewHolder>
            )
        }

        homeViewModel.totalItems.observe(viewLifecycleOwner) { items ->
            homeViewModel.totalCost.observe(viewLifecycleOwner) { cost ->
                if (items > 0) {
                    createSnackBar(root, "Items: $items | Rs. $cost", "View Cart") {
                        val sharedPreferences = activity?.getSharedPreferences(USER_DATA_KEY, Context.MODE_PRIVATE)
                        if (sharedPreferences?.contains(USER_ID_KEY) == true) {
                            Intent(
                                requireContext().applicationContext,
                                CartActivity::class.java
                            ).apply {
                                startActivity(this)
                            }
                        } else {
                            createToastMessage("You haven't signed in, please sign in")
                            Intent(
                                requireContext().applicationContext,
                                LoginActivity::class.java
                            ).apply {
                                startActivity(this)
                            }
                        }
                    }
                }
            }
        }

        homeViewModel.getItems("Pizza")
        getTotalItemsAndCost()

        getExtra()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setCategory(category: String) {
        this.category = category
    }

    override fun onCategoryClick(position: Int) {
        homeViewModel.getItems(list[position])
    }

    private fun getExtra() {
        val userData = activity?.intent?.extras
        userData?.let {
            val user = it.getParcelable<User>(USER_DATA)
            val sharedPreferences = activity?.getSharedPreferences(USER_DATA_KEY, Context.MODE_PRIVATE)

            sharedPreferences?.edit().apply {
                this!!.putString(USER_ID_KEY, user?.id)
                this!!.putString(USER_FIRSTNAME_KEY, user?.firstName)
                this!!.putString(USER_LASTNAME_KEY, user?.lastName)
                this!!.putString(USER_EMAIL_KEY, user?.email)
                this!!.putString(USER_MOB_NUMBER_KEY, user?.mobNum)
                apply()

                val res = sharedPreferences?.all?.get(USER_FIRSTNAME_KEY)
                Log.d("home", res.toString())
            }

        }
    }

    fun getTotalItemsAndCost() {
        homeViewModel.getTotalItems()
        homeViewModel.getTotalCost()
    }

}