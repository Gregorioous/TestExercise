package com.example.testexercise.utills

import android.app.AlertDialog
import android.content.Context
import com.example.testexercise.R

fun showLoginFailedDialog(context: Context, messageResId: String) {
    val builder = AlertDialog.Builder(context)
    try {
        builder.setMessage(messageResId)
        builder.setPositiveButton(R.string.error_dialog_ok, null)
    } catch (e: Exception) {
        showDialogSomethingWentWrong(context)
        return
    }
    builder.show()
}

fun showDialogSomethingWentWrong(context: Context) {
    val builder = AlertDialog.Builder(context)
    builder.setMessage(R.string.error_something_gone_wrong)
    builder.setPositiveButton(R.string.error_dialog_ok, null)
    builder.show()
}