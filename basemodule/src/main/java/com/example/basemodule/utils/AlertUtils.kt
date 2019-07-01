package com.example.basemodule.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.NonNull

class AlertUtils {
    companion object {
        fun shortToast(@NonNull context: Context?, msg: String) {
            if (context == null) {
                return
            }
            showToast(context, msg, Toast.LENGTH_SHORT)
        }

        fun longToast(@NonNull context: Context?, msg: String) {
            if (context == null) {
                return
            }
            showToast(context, msg, Toast.LENGTH_LONG)
        }

        fun showToast(context: Context, msg: String, length: Int) {
            if (context == null) {
                return
            }
            Toast.makeText(context, msg, length).show()
        }

        fun showMessage(context: Context, message: String) {
            if (context is Activity)
                context.runOnUiThread { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }
            else
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

            LogUtils.log("Context=" + context.javaClass.simpleName + "Toast" + message)
        }
    }
}