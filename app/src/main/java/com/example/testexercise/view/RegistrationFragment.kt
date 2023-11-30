package com.example.testexercise.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testapplication.api.response.ResponseToken
import com.example.testexercise.R
import com.example.testexercise.databinding.FragmentAutorizationBinding
import com.example.testexercise.utills.showLoginFailedDialog
import com.example.testexercise.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private var _binding: FragmentAutorizationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAutorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginbtn.setOnClickListener { doLogin() }

        viewModel.loginResponseToken.observe(viewLifecycleOwner) {
            if (it.data != null) {
                it.data.let { responseToken ->
                    responceTokenObserverAction(responseToken)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val userText = binding.usernameEdittext.text.toString()
        val passwordText = binding.passwordEditText.text.toString()
        outState.putCharSequence(SAVELOGIN, userText)
        outState.putCharSequence(SAVEPASSWORD, passwordText)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val userText = savedInstanceState?.getCharSequence("savedText")
        val passwordText = savedInstanceState?.getCharSequence("savedText")
        binding.usernameEdittext.setText(userText)
        binding.passwordEditText.setText(passwordText)
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
        viewModel.login(userLogin = login, userPassword = pwd)
        showLoading()
    }

    private fun responceTokenObserverAction(responseToken: ResponseToken) {
        if (responseToken.success == "true") {
            val userName = binding.usernameEdittext.text.toString().trim()
            val userToken = responseToken.response?.token ?: ""

            val bundle = Bundle().apply {
                putString("userName", userName)
                putString("userToken", userToken)
            }
            findNavController().navigate(R.id.listPaymentFragment, bundle)
        } else {
            processError(requireContext(), "Error:\n ${responseToken.error?.error_msg}")
            binding.usernameEdittext.text?.clear()
            binding.passwordEditText.text?.clear()
            stopLoading()
        }
    }


    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    fun processLogin(data: ResponseToken) {
        showToast("Success:" + data.success)

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

    companion object {
        private const val SAVELOGIN = "savedLoginText"
        private const val SAVEPASSWORD = "savedPasswordText"
    }

}
