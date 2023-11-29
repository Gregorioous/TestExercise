package com.example.testexercise.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testexercise.R
import com.example.testexercise.databinding.FragmentListPaymentBinding
import com.example.testexercise.utills.BaseResponse
import com.example.testexercise.utills.PaymentAdapter
import com.example.testexercise.utills.showLoginFailedDialog
import com.example.testexercise.viewmodel.PaymentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPaymentFragment : Fragment() {

    private val paymentsViewModel: PaymentsViewModel by viewModel()
    private var adapter = PaymentAdapter()
    private var _binding: FragmentListPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListPaymentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPaymentsList()
        binding.btnLogout.setOnClickListener { logOut() }
    }

    private fun logOut() {
        paymentsViewModel.setNullToToken()
        val fragmentTransaction: FragmentTransaction = childFragmentManager.beginTransaction()
        val authorizationFragment: Fragment = RegistrationFragment()
        fragmentTransaction.replace(R.id.main_container, authorizationFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun getPaymentsList() {
        paymentsViewModel.getPaymentsList().observe(viewLifecycleOwner) { response ->
            when (response) {
                is BaseResponse.Loading -> binding.btnLogout.isEnabled = false
                is BaseResponse.Failed -> context?.let { processError(it, response.error) }
                is BaseResponse.Success -> createRecyclerView(response.response.responsePayments)
            }
            binding.btnLogout.isEnabled = true
        }
    }

    private fun createRecyclerView(paymentsList: List<ResponsePaymentsList.ResponsePayments>) {
        adapter.setPayments(paymentsList)
        binding.recyclerView.adapter = adapter
    }

    fun processError(
        context: Context, errorMassage: String
    ) {
        showLoginFailedDialog(context, errorMassage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}