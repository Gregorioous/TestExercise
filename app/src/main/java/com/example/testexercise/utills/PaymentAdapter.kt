package com.example.testexercise.utills

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testexercise.databinding.ItemRecyclerBinding

class PaymentAdapter : RecyclerView.Adapter<PaymentAdapter.PaymentHolder>() {

    private var payments: List<ResponsePaymentsList.ResponsePayments> = listOf()

    fun setPayments(payments: List<ResponsePaymentsList.ResponsePayments>) {
        this.payments = payments
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
        val binding = ItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaymentHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        val itemPayment = payments[position]
        holder.bindPayment(itemPayment.title, itemPayment.amount, itemPayment.created)
    }


    override fun getItemCount(): Int = payments.size

    class PaymentHolder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var title: String? = ""
        private var amount: String? = null
        private var created: String? = null
        private var Title: TextView = binding.titleTextView
        private var Amount: TextView = binding.amountTextView
        private var Created: TextView = binding.createdTextView


        fun bindPayment(title: String?, amount: String?, created: Long?) {
            this.amount = amount.toString()
            this.created = created?.let { DateConverter.convertTimestampToDate(it) }
            this.title = title

            createView()
        }

        private fun createView() {

            Title.text = this.title.toString()
            Amount.text = this.amount
            Created.text = this.created
        }
    }


}