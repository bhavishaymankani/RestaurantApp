package com.bhavishaymankani.customrestaurant.base

import android.content.Context
import android.view.View
import android.widget.Adapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.customrestaurant.R
import com.bhavishaymankani.customrestaurant.ui.login.login.SigninViewModel
import com.bhavishaymankani.customrestaurant.ui.login.register.RegisterViewModel
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment() {
    fun signUpUser(
        viewModel: RegisterViewModel,
        firstName: EditText,
        lastName: EditText,
        email: EditText,
        mobNum: EditText,
        password: EditText,
        confirmPassword: EditText,
        checkBox: CheckBox
    ) {

        val fields = validateETFields(firstName, lastName, email, mobNum, password, confirmPassword)
        if (fields && checkBox.isChecked && password.text.toString() == confirmPassword.text.toString()) {
            viewModel.addUser(
                firstName.text.toString().trim { it <= ' ' },
                lastName.text.toString().trim { it <= ' ' },
                email.text.toString().trim { it <= ' ' },
                password.text.toString().trim { it <= ' ' },
                mobNum.text.toString().trim { it <= ' ' }
            )
        }

    }

    fun loginUser(
        viewModel: SigninViewModel,
        email: EditText,
        password: EditText
    ) {
        val fields = validateETFields(email, password)
        if (fields) {
            viewModel.validateUser(
                email.text.toString().trim { it <= ' ' },
                password.text.toString().trim { it <= ' ' }
            )
        }
    }

    fun createRecyclerview(context: Context, recyclerView: RecyclerView, recyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, isHorizontal: Boolean = false ) {
        recyclerView.apply {
            layoutManager = if (!isHorizontal) LinearLayoutManager(context) else LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter =  recyclerViewAdapter
            setHasFixedSize(true)
        }
    }

    fun createToastMessage(message: String) {
        Toast.makeText(requireContext().applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun createSnackBar(view: View, message: String, actionText: String, listener: View.OnClickListener) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).apply {
            this.view.apply {
                setBackgroundColor(ContextCompat.getColor(view.context, R.color.orange_500))
            }
            setAction(actionText, listener)
            setAnchorView(R.id.nav_view)
            setTextColor(ContextCompat.getColor(view.context, R.color.theme_white))
            setActionTextColor(ContextCompat.getColor(view.context, R.color.theme_white))
            setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            show()
        }
    }


    private fun validateETFields(
        email: EditText,
        password: EditText
    ) : Boolean {
        return when {
            email.text.toString().isEmpty() -> {
                editTextEmptyMessage(email, resources.getString(R.string.email_empty_message))
            }
            password.text.toString().isEmpty() -> {
                editTextEmptyMessage(password, resources.getString(R.string.password_empty_message))
            }
            else -> {
                true
            }
        }
    }

    private fun validateETFields(
        firstName: EditText,
        lastName: EditText,
        email: EditText,
        mobNum: EditText,
        password: EditText,
        confirmPassword: EditText
    ) : Boolean{

        return when {
            firstName.text.toString().isEmpty() -> {
                editTextEmptyMessage(firstName, resources.getString(R.string.first_name_empty_message))
            }
            lastName.text.toString().isEmpty() -> {
                editTextEmptyMessage(lastName, resources.getString(R.string.last_name_empty_message))
            }
            mobNum.text.toString().isEmpty() -> {
                editTextEmptyMessage(mobNum, resources.getString(R.string.mob_num_empty_message))
            }
            email.text.toString().isEmpty() -> {
                editTextEmptyMessage(email, resources.getString(R.string.email_empty_message))
            }
            password.text.toString().isEmpty() -> {
                editTextEmptyMessage(password, resources.getString(R.string.password_empty_message))
            }
            confirmPassword.text.toString().isEmpty() -> {
                editTextEmptyMessage(confirmPassword, resources.getString(R.string.confirm_password_empty_message))
            }
            else -> {
                true
            }
        }
    }


    private fun editTextEmptyMessage(et: EditText, emptyMessage: String?) : Boolean {
        et.error = emptyMessage
        et.requestFocus()

        return false
    }
}