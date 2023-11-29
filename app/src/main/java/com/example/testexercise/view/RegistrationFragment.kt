package com.example.testexercise.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.testapplication.api.response.ResponseToken
import com.example.testexercise.R
import com.example.testexercise.api.AuthInterceptor.Companion.token
import com.example.testexercise.databinding.FragmentAutorizationBinding
import com.example.testexercise.utills.BaseResponse
import com.example.testexercise.utills.showLoginFailedDialog
import com.example.testexercise.viewmodel.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegistrationFragment : Fragment() {
    private var _binding: FragmentAutorizationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAutorizationBinding.inflate(inflater, container, false)

        binding.loginbtn.setOnClickListener {
            doLogin()

        }


        return binding.root
    }

    private fun createRequestToken(login: String, password: String) {
        viewModel.getToken(login, password).observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.response)
                }

                is BaseResponse.Failed -> {
                    context?.let { it1 -> processError(it1, it.error) }
                }

                else -> {
                    stopLoading()
                }
            }
        }
    }

    private fun navigateToPayment() {
        viewModel.setToken(token)
        val fragment = ListPaymentFragment()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }


    fun doLogin() {
        val login = binding.usernameEdittext.text.toString()
        val pwd = binding.passwordEditText.text.toString()

        if (login.length < 4) {
            binding.usernameInputLayout.error = "Логин должен содержать не менее 4 символов"
            return
        }

        if (pwd.length < 5) {
            binding.passwordInputLayout.error = "Пароль должен содержать не менее 5 символов"
            return
        }
        createRequestToken(login = login, password = pwd)
    }


    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    fun processLogin(data: ResponseToken) {
        showToast("Success:" + data.success)
        navigateToPayment()
    }

    fun processError(
        context: Context, errorMassage: String
    ) {
        showLoginFailedDialog(context, errorMassage)
    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
