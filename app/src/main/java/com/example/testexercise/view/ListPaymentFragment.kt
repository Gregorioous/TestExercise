package com.example.testexercise.view

 import android.os.Bundle
 import android.view.LayoutInflater
 import android.view.View
 import android.view.ViewGroup
 import androidx.fragment.app.Fragment
 import androidx.fragment.app.viewModels
 import androidx.navigation.fragment.findNavController
 import androidx.recyclerview.widget.LinearLayoutManager
 import com.example.testapplication.api.response.ResponsePaymentsList
 import com.example.testexercise.R
 import com.example.testexercise.data.api.model.ResponsePayments
 import com.example.testexercise.databinding.FragmentListPaymentBinding
 import com.example.testexercise.utills.PaymentAdapter
 import com.example.testexercise.viewmodel.PaymentsViewModel
 import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPaymentFragment : Fragment() {

    private val paymentsViewModel by viewModels<PaymentsViewModel>()
    private lateinit var adapter: PaymentAdapter
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

        navigation()
        Args()

        paymentsViewModel.responsePayments.observe(viewLifecycleOwner) {
            if (it.data != null) {
                it.data.let { responsePayments ->
                    responsePaymentsObserverAction(responsePayments)
                }
            }
        }
    }

    private fun navigation() {
        binding.logoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_listPaymentFragment2_to_registrationFragment2)
        }
    }

    private fun responsePaymentsObserverAction(responsePayments: ResponsePaymentsList) =
        with(binding) {
            if (responsePayments.response != null) {
                adapter(responsePayments.response)
            }
        }

    private fun Args() {
        val userName = arguments?.getString("userName", "")
        val userToken = arguments?.getString("userToken", "")
        binding.tvLayoutTitle.text = userName
        userToken?.let { paymentsViewModel.getUsersPayments(it) }
    }

    private fun adapter(payments: List<ResponsePayments>) {
        adapter = PaymentAdapter()
        adapter.payments = payments
        val layoutManager = LinearLayoutManager(this.context)
        binding.apply {
            binding.recyclerView.layoutManager = layoutManager
            binding.recyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}