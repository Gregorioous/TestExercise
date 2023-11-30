package com.example.testexercise.utills

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testexercise.data.api.model.ResponsePayments
import com.example.testexercise.databinding.ItemRecyclerBinding

class PaymentAdapter : RecyclerView.Adapter<PaymentAdapter.PaymentsListViewHolder>() {

    var payments: List<ResponsePayments> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged() //да да, я знаю, но DiffUtill здесь точно лишним будет
        }

    inner class PaymentsListViewHolder(val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PaymentsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerBinding.inflate(inflater, parent, false)
        return PaymentsListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PaymentAdapter.PaymentsListViewHolder,
        position: Int,
    ) {
        val payment = payments[position]
        with(holder.binding) {
            titleTextView.text = payment.title.toString().ifEmpty { "" }
            amountTextView.text = payment.amount.toString()
            createdTextView.text = payment.created.let { DateConverter.convertTimestampToDate(it) }
        }
    }


    override fun getItemCount(): Int {
        return payments.size
    }
}