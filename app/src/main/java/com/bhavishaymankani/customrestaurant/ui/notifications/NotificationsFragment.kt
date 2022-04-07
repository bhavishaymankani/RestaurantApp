package com.bhavishaymankani.customrestaurant.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bhavishaymankani.customrestaurant.R
import com.bhavishaymankani.customrestaurant.databinding.FragmentNotificationsBinding
import com.bhavishaymankani.customrestaurant.datasource.local.CartItemDatabase
import com.bhavishaymankani.customrestaurant.ui.home.CartItemRepository

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val cartItemRepository = CartItemRepository(CartItemDatabase(requireActivity().application.applicationContext))
        val factory = CartViewModelFactory(cartItemRepository)

        notificationsViewModel =
            ViewModelProvider(this, factory).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.cartItems.observe(viewLifecycleOwner, Observer {
            it.map { item ->
                textView.append(item.toString())
            }
        })


        notificationsViewModel.getCartItems()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}